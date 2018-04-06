package com.anfelisa.ace;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public String getName() {
		return "Todo MVC";
	}

	public static String getVersion() {
		return "2.1.0";
	}

	@Override
	public void initialize(Bootstrap<AppConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		
		bootstrap.addCommand(new EventReplayCommand(this));
	}

	@Override
	public void run(AppConfiguration configuration, Environment environment) throws ClassNotFoundException {
		LOG.info("running version {}", getVersion());

		AceDao.setSchemaName(null);

		final DBIFactory factory = new DBIFactory();

		DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "todo");

		if (ServerConfiguration.REPLAY.equals(configuration.getServerConfiguration().getMode())) {
			AceController.setAceExecutionMode(AceExecutionMode.REPLAY);
			environment.jersey().register(new PrepareE2EResource(jdbi));
			environment.jersey().register(new StartE2ESessionResource(jdbi));
			environment.jersey().register(new StopE2ESessionResource());
			environment.jersey().register(new GetServerTimelineResource(jdbi));
		} else if (ServerConfiguration.DEV.equals(configuration.getServerConfiguration().getMode())) {
			AceController.setAceExecutionMode(AceExecutionMode.DEV);
			environment.jersey().register(new GetServerTimelineResource(jdbi));
		} else {
			AceController.setAceExecutionMode(AceExecutionMode.LIVE);
			environment.jersey().register(new GetServerTimelineResource(jdbi));  // do not register GetServerTimelineResource in a 'real' live environment
		}

		environment.jersey().register(new GetServerInfoResource());

		DBIExceptionsBundle dbiExceptionsBundle = new DBIExceptionsBundle();
		environment.jersey().register(dbiExceptionsBundle);

		environment.jersey().register(RolesAllowedDynamicFeature.class);

		com.anfelisa.todo.AppRegistration.registerResources(environment, jdbi);
		com.anfelisa.todo.AppRegistration.registerConsumers();

	}

}