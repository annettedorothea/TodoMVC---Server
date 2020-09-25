package de.acegen;

import io.dropwizard.setup.Environment;

public class AppRegistration {

	public static void registerResources(Environment environment, PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration,
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		com.anfelisa.todo.AppRegistration.registerResources(environment, persistenceConnection, appConfiguration, daoProvider, viewProvider);
	}

	public static void registerConsumers(ViewProvider viewProvider) {
		com.anfelisa.todo.AppRegistration.registerConsumers(viewProvider);
	}
}

