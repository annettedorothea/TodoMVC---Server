package com.anfelisa.ace;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<CustomAppConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(App.class);

	static EmailService EMAIL_SERVICE;

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public String getName() {
		return "Todo MVC";
	}

	public static String getVersion() {
		return "4.0.1";
	}

	@Override
	public void initialize(Bootstrap<CustomAppConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		
		bootstrap.addCommand(new EventReplayCommand(this, new DaoProvider()));
	}

	@Override
	public void run(CustomAppConfiguration configuration, Environment environment) throws ClassNotFoundException {
		LOG.info("running version {}", getVersion());

		EMAIL_SERVICE = new EmailService(configuration.getEmail());

		DaoProvider daoProvider = new DaoProvider();
		ViewProvider viewProvider = new ViewProvider(daoProvider);

		AceDao.setSchemaName(null);

		final JdbiFactory factory = new JdbiFactory();

		Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "todo");

		String mode = configuration.getServerConfiguration().getMode();
		if (ServerConfiguration.REPLAY.equals(mode)) {
			environment.jersey().register(new PrepareE2EResource(jdbi, daoProvider, viewProvider));
			environment.jersey().register(new StartE2ESessionResource(jdbi, daoProvider));
			environment.jersey().register(new StopE2ESessionResource());
			environment.jersey().register(new GetServerTimelineResource(jdbi));
		} else if (ServerConfiguration.DEV.equals(mode)) {
			environment.jersey().register(new GetServerTimelineResource(jdbi));
		}

		environment.jersey().register(new GetServerInfoResource());

		JdbiExceptionsBundle dbiExceptionsBundle = new JdbiExceptionsBundle();
		environment.jersey().register(dbiExceptionsBundle);

		environment.jersey().register(RolesAllowedDynamicFeature.class);

		new com.anfelisa.todo.AppRegistration().registerResources(environment, jdbi, configuration, daoProvider, viewProvider);
		new com.anfelisa.todo.AppRegistration().registerConsumers(viewProvider, mode);

	}

	public static void reportException(Exception x) {
		if (EMAIL_SERVICE != null) {
			try {
				EMAIL_SERVICE.sendEmail("!!! Todo exception !!!", x.getMessage());
			} catch (Exception e) {
				LOG.error("failed to notify about exception", x.getMessage());
			}
		}
	}

}