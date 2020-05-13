package de.acegen;

import io.dropwizard.setup.Environment;

public class AppRegistration {

	public static void registerResources(Environment environment, PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration,
			IDaoProvider daoProvider, ViewProvider viewProvider, E2E e2e) {
		com.anfelisa.todo.AppRegistration.registerResources(environment, persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e);
	}

	public static void registerConsumers(ViewProvider viewProvider, String mode) {
		com.anfelisa.todo.AppRegistration.registerConsumers(viewProvider, mode);
	}
}

