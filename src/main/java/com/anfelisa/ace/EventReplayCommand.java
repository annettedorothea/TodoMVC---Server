package com.anfelisa.ace;

import java.lang.reflect.Constructor;
import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;

public class EventReplayCommand extends EnvironmentCommand<AppConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(EventReplayCommand.class);

	private AceDao aceDao = new AceDao();

	protected EventReplayCommand(Application<AppConfiguration> application) {
		super(application, "replay", "truncates views and replays events");
	}

	@Override
	protected void run(Environment environment, Namespace namespace, AppConfiguration configuration) throws Exception {
		if (AceController.getAceExecutionMode() == AceExecutionMode.LIVE) {	
			throw new RuntimeException("we won't truncate all views and replay events in a live environment");
		}
		if (AceController.getAceExecutionMode() == AceExecutionMode.REPLAY) {	
			throw new RuntimeException("replay events in a replay environment doesn't make sense");
		}
		
		AceDao.setSchemaName(null);

		final DBIFactory factory = new DBIFactory();

		DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "data-source-name");

		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		LOG.info("START EVENT REPLAY");
		try {
			databaseHandle.beginTransaction();
			Handle handle = databaseHandle.getHandle();
			AppUtils.truncateAllViews(handle);

			List<ITimelineItem> timeline = aceDao.selectTimeline(handle);
			E2E.timeline = timeline;

			int eventCount = 0;
			ITimelineItem nextAction = E2E.selectNextAction(null);
			while (nextAction != null) {
				if (!nextAction.getMethod().equalsIgnoreCase("GET")) {
					ITimelineItem nextEvent = E2E.selectEvent(nextAction.getUuid());
					if (nextEvent != null) {
						try {
							Class<?> cl = Class.forName(nextEvent.getName());
							Constructor<?> con = cl.getConstructor(DatabaseHandle.class);
							IEvent event = (IEvent) con.newInstance(databaseHandle);
							event.initEventData(nextEvent.getData());
							event.notifyListeners();
							eventCount++;
							LOG.info("published " + nextEvent.getUuid() + " - " + nextEvent.getName());
						} catch (Exception e) {
							LOG.error("failed to replay event " + nextEvent.getUuid() + " - " + nextEvent.getName());
							LOG.error(e.getMessage());
						}
					}
				}
				nextAction = E2E.selectNextAction(nextAction.getUuid());
			}

			databaseHandle.commitTransaction();

			LOG.info("EVENT REPLAY FINISHED: successfully replayed " + eventCount + " events");
			
		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			LOG.info("EVENT REPLAY ABORTED " + e.getMessage());
			throw e;
		} finally {
			databaseHandle.close();
		}

	}

}

