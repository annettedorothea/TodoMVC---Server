package com.anfelisa.ace;

import org.jdbi.v3.core.Jdbi;

import io.dropwizard.setup.Environment;

public class AppRegistration {

	public static void registerResources(Environment environment, Jdbi jdbi, CustomAppConfiguration appConfiguration,
			IDaoProvider daoProvider, ViewProvider viewProvider, E2E e2e) {
		com.anfelisa.todo.AppRegistration.registerResources(environment, jdbi, appConfiguration, daoProvider, viewProvider, e2e);
	}

	public static void registerConsumers(ViewProvider viewProvider, String mode) {
		com.anfelisa.todo.AppRegistration.registerConsumers(viewProvider, mode);
	}
}

