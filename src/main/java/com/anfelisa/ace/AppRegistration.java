package com.anfelisa.ace;

import org.jdbi.v3.core.Jdbi;

import io.dropwizard.setup.Environment;

public class AppRegistration {

	public static void registerResources(Environment environment, Jdbi jdbi, CustomAppConfiguration appConfiguration,
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		com.anfelisa.todo.AppRegistration.registerResources(environment, jdbi, appConfiguration, daoProvider, viewProvider);
	}

	public static void registerConsumers(ViewProvider viewProvider, String mode) {
		com.anfelisa.todo.AppRegistration.registerConsumers(viewProvider, mode);
	}
}

