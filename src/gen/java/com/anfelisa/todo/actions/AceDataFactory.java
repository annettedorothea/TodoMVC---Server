package com.anfelisa.todo.actions;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.IDataContainer;
import com.anfelisa.ace.JodaObjectMapper;
import com.anfelisa.todo.data.*;
import com.fasterxml.jackson.databind.DeserializationFeature;		

public class AceDataFactory {
	
	private static JodaObjectMapper mapper = new JodaObjectMapper();
	private static final Logger LOG = LoggerFactory.getLogger(AceDataFactory.class);

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static IDataContainer createAceData(String className, String json) {
		try {
			if (className.equals("com.anfelisa.todo.actions.GetAllTodosAction")) {
				TodoListData data = mapper.readValue(json, TodoListData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.CreateTodoAction") ||
					className.equals("com.anfelisa.todo.commands.CreateTodoCommand") ||
					className.equals("com.anfelisa.todo.events.CreateTodoSuccessEvent")
			) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.ToggleTodoAction") ||
					className.equals("com.anfelisa.todo.commands.ToggleTodoCommand") ||
					className.equals("com.anfelisa.todo.events.ToggleTodoSuccessEvent")
			) {
				TodoToggleData data = mapper.readValue(json, TodoToggleData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.ToggleAllAction") ||
					className.equals("com.anfelisa.todo.commands.ToggleAllCommand") ||
					className.equals("com.anfelisa.todo.events.ToggleAllSuccessEvent")
			) {
				ToggleAllData data = mapper.readValue(json, ToggleAllData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.UpdateTodoAction") ||
					className.equals("com.anfelisa.todo.commands.UpdateTodoCommand") ||
					className.equals("com.anfelisa.todo.events.UpdateTodoSuccessEvent")
			) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.DeleteTodoAction") ||
					className.equals("com.anfelisa.todo.commands.DeleteTodoCommand") ||
					className.equals("com.anfelisa.todo.events.DeleteTodoSuccessEvent")
			) {
				TodoIdData data = mapper.readValue(json, TodoIdData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.ClearDoneAction") ||
					className.equals("com.anfelisa.todo.commands.ClearDoneCommand") ||
					className.equals("com.anfelisa.todo.events.ClearDoneSuccessEvent")
			) {
				ClearDoneData data = mapper.readValue(json, ClearDoneData.class);
				data.migrateLegacyData(json);
				return data;
			}
		} catch (IOException e) {
			LOG.error("failed to create ace data {} with data {}", className, json, e);
		}

		return null;
	}
}

