package de.acegen;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<CustomAppConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(App.class);

	static EmailService EMAIL_SERVICE;
	
	static IDaoProvider daoProvider;
	
	public static ViewProvider viewProvider;

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public String getName() {
		return "Todo MVC";
	}

	public static String getVersion() {
		return "0.6.0";
	}

	@Override
	public void initialize(Bootstrap<CustomAppConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});

		bootstrap.addCommand(new EventReplayCommand(this));
	}

	@Override
	public void run(CustomAppConfiguration configuration, Environment environment) throws ClassNotFoundException {
		LOG.info("running version {}", getVersion());

		EMAIL_SERVICE = new EmailService(configuration.getEmail());

		daoProvider = DaoProvider.create();
		viewProvider = ViewProvider.create(daoProvider, configuration);

		final JdbiFactory factory = new JdbiFactory();

		Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "todo");
		
		E2E e2e = new E2E();

		String mode = configuration.getServerConfiguration().getMode();
		if (ServerConfiguration.REPLAY.equals(mode)) {
			environment.jersey().register(new PrepareE2EResource(jdbi, daoProvider, viewProvider, e2e, configuration));
			environment.jersey().register(new StartE2ESessionResource(jdbi, daoProvider, e2e, configuration));
			environment.jersey().register(new StopE2ESessionResource(e2e, configuration));
			environment.jersey().register(new GetServerTimelineResource(jdbi, configuration));
			LOG.warn("You are running in REPLAY mode. This is a security risc.");
		} else if (ServerConfiguration.DEV.equals(mode)) {
			environment.jersey().register(new GetServerTimelineResource(jdbi, configuration));
			LOG.warn("You are running in DEV mode. This is a security risc.");
		}
		
		environment.jersey().register(new JsonProcessingExceptionMapper(true));

		environment.jersey().register(new GetServerInfoResource());

		JdbiExceptionsBundle dbiExceptionsBundle = new JdbiExceptionsBundle();
		environment.jersey().register(dbiExceptionsBundle);

		environment.jersey().register(RolesAllowedDynamicFeature.class);

		AppRegistration.registerResources(environment, new PersistenceConnection(jdbi), configuration, daoProvider, viewProvider, e2e);
		AppRegistration.registerConsumers(viewProvider, mode);

		configureCors(environment);
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
	
	private void configureCors(Environment environment) {
		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

		// Configure CORS parameters
		cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
				"X-Requested-With,Content-Type,Accept,Origin,Authorization");
		cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

		// Add URL mapping
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

	}


}