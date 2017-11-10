package com.anfelisa.todo.app;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.AceController;
import com.anfelisa.ace.AceDao;
import com.anfelisa.ace.AceExecutionMode;

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

	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void initialize(Bootstrap<AppConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
	}

	@Override
	public void run(AppConfiguration configuration, Environment environment) throws ClassNotFoundException {
		LOG.info("running version {}", getVersion());

		AceDao.setSchemaName(null);

		final DBIFactory factory = new DBIFactory();

		DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "todo");
		DBI jdbiTimeline = null;

		if (configuration.getTimelineDataSourceFactory().getUrl() != null) {
			AceController.setAceExecutionMode(AceExecutionMode.REPLAY);
			jdbiTimeline = factory.build(environment, configuration.getTimelineDataSourceFactory(),
					"todo_replay");
			environment.jersey().register(new ClearDatabaseResource(jdbi));
			environment.jersey().register(new PrepareDatabaseResource(jdbi, jdbiTimeline));
		} else {
			AceController.setAceExecutionMode(AceExecutionMode.LIVE);
			environment.jersey().register(new MigrateDatabaseResource(jdbi));
		}

		DBIExceptionsBundle dbiExceptionsBundle = new DBIExceptionsBundle();
		environment.jersey().register(dbiExceptionsBundle);

		environment.jersey().register(RolesAllowedDynamicFeature.class);

		com.anfelisa.todo.AppRegistration.registerResources(environment, jdbi, jdbiTimeline);
		com.anfelisa.todo.AppRegistration.registerConsumers();

	}

}