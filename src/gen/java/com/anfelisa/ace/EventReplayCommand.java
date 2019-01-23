package com.anfelisa.ace;

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

	protected EventReplayCommand(Application<CustomAppConfiguration> application) {
		super(application, "replay", "truncates views and replays events");
	}

	@Override
	protected void run(Environment environment, Namespace namespace, CustomAppConfiguration configuration)
			throws Exception {
		if (ServerConfiguration.LIVE.equals(configuration.getServerConfiguration().getMode())) {
			throw new RuntimeException("we won't truncate all views and replay events in a live environment");
		}
		if (ServerConfiguration.REPLAY.equals(configuration.getServerConfiguration().getMode())) {
			throw new RuntimeException("replay events in a replay environment doesn't make sense");
		}

		IDaoProvider daoProvider = DaoProvider.create();
		ViewProvider viewProvider = ViewProvider.create(daoProvider, configuration);

		final JdbiFactory factory = new JdbiFactory();
		Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "data-source-name");
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);

		AppRegistration.registerConsumers(viewProvider, ServerConfiguration.REPLAY);

		LOG.info("START EVENT REPLAY");
		try {
			databaseHandle.beginTransaction();
			Handle handle = databaseHandle.getHandle();
			daoProvider.truncateAllViews(handle);

			List<ITimelineItem> timeline = daoProvider.getAceDao().selectReplayTimeline(handle);

			int i = 0;
			for (ITimelineItem nextEvent : timeline) {
				IEvent event = EventFactory.createEvent(nextEvent.getName(), nextEvent.getData(), databaseHandle,
						daoProvider, viewProvider);
				event.notifyListeners();
				i++;
				if (i%1000 == 0) {
					LOG.info("published " + i + " events");
				}
				//LOG.info("published " + nextEvent.getUuid() + " - " + nextEvent.getName());
			}

			databaseHandle.commitTransaction();

			LOG.info("EVENT REPLAY FINISHED: successfully replayed " + i + " events");

		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			LOG.info("EVENT REPLAY ABORTED " + e.getMessage());
			throw e;
		} finally {
			databaseHandle.close();
		}

	}

}
