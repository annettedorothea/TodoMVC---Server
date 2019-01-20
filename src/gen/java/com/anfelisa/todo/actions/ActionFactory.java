package com.anfelisa.todo.actions;

import java.io.IOException;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.IAction;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.JodaObjectMapper;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.*;
import com.fasterxml.jackson.databind.DeserializationFeature;		

public class ActionFactory {
	
	private static JodaObjectMapper mapper = new JodaObjectMapper();
	private static final Logger LOG = LoggerFactory.getLogger(ActionFactory.class);

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static IAction createAction(String actionClass, String json, Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		try {
			if (actionClass.equals("com.anfelisa.todo.actions.CreateTodoAction")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				CreateTodoAction action = new CreateTodoAction(jdbi, appConfiguration, daoProvider, viewProvider);
				action.setActionData(data);
				return action;
			}
			if (actionClass.equals("com.anfelisa.todo.actions.ToggleTodoAction")) {
				TodoToggleData data = mapper.readValue(json, TodoToggleData.class);
				data.migrateLegacyData(json);
				ToggleTodoAction action = new ToggleTodoAction(jdbi, appConfiguration, daoProvider, viewProvider);
				action.setActionData(data);
				return action;
			}
			if (actionClass.equals("com.anfelisa.todo.actions.ToggleAllAction")) {
				ToggleAllData data = mapper.readValue(json, ToggleAllData.class);
				data.migrateLegacyData(json);
				ToggleAllAction action = new ToggleAllAction(jdbi, appConfiguration, daoProvider, viewProvider);
				action.setActionData(data);
				return action;
			}
			if (actionClass.equals("com.anfelisa.todo.actions.UpdateTodoAction")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				UpdateTodoAction action = new UpdateTodoAction(jdbi, appConfiguration, daoProvider, viewProvider);
				action.setActionData(data);
				return action;
			}
			if (actionClass.equals("com.anfelisa.todo.actions.DeleteTodoAction")) {
				TodoIdData data = mapper.readValue(json, TodoIdData.class);
				data.migrateLegacyData(json);
				DeleteTodoAction action = new DeleteTodoAction(jdbi, appConfiguration, daoProvider, viewProvider);
				action.setActionData(data);
				return action;
			}
			if (actionClass.equals("com.anfelisa.todo.actions.ClearDoneAction")) {
				ClearDoneData data = mapper.readValue(json, ClearDoneData.class);
				data.migrateLegacyData(json);
				ClearDoneAction action = new ClearDoneAction(jdbi, appConfiguration, daoProvider, viewProvider);
				action.setActionData(data);
				return action;
			}
		} catch (IOException e) {
			LOG.error("failed to create action {} with data {}", actionClass, json, e);
		}

		return null;
	}
}

