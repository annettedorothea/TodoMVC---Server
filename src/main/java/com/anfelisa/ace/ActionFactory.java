package com.anfelisa.ace;

import org.jdbi.v3.core.Jdbi;

public class ActionFactory {
	public static IAction createAction(String actionClass, String json, Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		if (actionClass.startsWith("com.anfelisa.todo.actions")) {
			return com.anfelisa.todo.actions.ActionFactory.createAction(actionClass, json, jdbi, appConfiguration, daoProvider,
					viewProvider);
		}
		return null;
	}
}

