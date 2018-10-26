package com.anfelisa.ace;

import java.lang.reflect.Constructor;
import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;

public class EventReplayCommand extends EnvironmentCommand<CustomAppConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(EventReplayCommand.class);

	private IDaoProvider daoProvider;
	
	protected EventReplayCommand(Application<CustomAppConfiguration> application, IDaoProvider daoProvider) {
		super(application, "replay", "truncates views and replays events");
		this.daoProvider = daoProvider;
	}

	@Override
	protected void run(Environment environment, Namespace namespace, CustomAppConfiguration configuration) throws Exception {
		if (ServerConfiguration.LIVE.equals(configuration.getServerConfiguration().getMode())) {	
			throw new RuntimeException("we won't truncate all views and replay events in a live environment");
		}
		if (ServerConfiguration.REPLAY.equals(configuration.getServerConfiguration().getMode())) {	
			throw new RuntimeException("replay events in a replay environment doesn't make sense");
		}
		
		final JdbiFactory factory = new JdbiFactory();

		Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "todo");

		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		
		ViewProvider viewProvider = new ViewProvider(daoProvider);
		
		LOG.info("START EVENT REPLAY");
		try {
			databaseHandle.beginTransaction();
			Handle handle = databaseHandle.getHandle();
			daoProvider.truncateAllViews(handle);

			List<ITimelineItem> timeline = daoProvider.getAceDao().selectTimeline(handle);
			E2E.timeline = timeline;
			
			String mode = configuration.getServerConfiguration().getMode();
			new com.anfelisa.todo.AppRegistration().registerConsumers(viewProvider, mode);

			int eventCount = 0;
			ITimelineItem nextAction = E2E.selectNextAction(null);
			while (nextAction != null) {
				if (!nextAction.getMethod().equalsIgnoreCase("GET")) {
					ITimelineItem nextEvent = E2E.selectEvent(nextAction.getUuid());
					if (nextEvent != null) {
						try {
							Class<?> cl = Class.forName(nextEvent.getName());
							Constructor<?> con = cl.getConstructor(DatabaseHandle.class, IDaoProvider.class, ViewProvider.class);
							IEvent event = (IEvent) con.newInstance(databaseHandle, daoProvider, viewProvider);
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

