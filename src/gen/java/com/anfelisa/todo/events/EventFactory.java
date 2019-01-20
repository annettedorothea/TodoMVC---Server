package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.IEvent;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.*;
import com.anfelisa.ace.JodaObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.anfelisa.ace.IDataContainer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventFactory {
	
	private static JodaObjectMapper mapper = new JodaObjectMapper();
	private static final Logger LOG = LoggerFactory.getLogger(EventFactory.class);

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static IEvent createEvent(String eventClass, String json, DatabaseHandle databaseHandle,
		IDaoProvider daoProvider, ViewProvider viewProvider) {
		try {
			if (eventClass.equals("com.anfelisa.todo.events.CreateTodoSuccessEvent")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				CreateTodoSuccessEvent event = new CreateTodoSuccessEvent(data, databaseHandle, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ToggleTodoSuccessEvent")) {
				TodoToggleData data = mapper.readValue(json, TodoToggleData.class);
				data.migrateLegacyData(json);
				ToggleTodoSuccessEvent event = new ToggleTodoSuccessEvent(data, databaseHandle, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ToggleAllSuccessEvent")) {
				ToggleAllData data = mapper.readValue(json, ToggleAllData.class);
				data.migrateLegacyData(json);
				ToggleAllSuccessEvent event = new ToggleAllSuccessEvent(data, databaseHandle, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.UpdateTodoSuccessEvent")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				UpdateTodoSuccessEvent event = new UpdateTodoSuccessEvent(data, databaseHandle, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.DeleteTodoSuccessEvent")) {
				TodoIdData data = mapper.readValue(json, TodoIdData.class);
				data.migrateLegacyData(json);
				DeleteTodoSuccessEvent event = new DeleteTodoSuccessEvent(data, databaseHandle, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ClearDoneSuccessEvent")) {
				ClearDoneData data = mapper.readValue(json, ClearDoneData.class);
				data.migrateLegacyData(json);
				ClearDoneSuccessEvent event = new ClearDoneSuccessEvent(data, databaseHandle, daoProvider, viewProvider);
				return event;
			}
		} catch (IOException e) {
			LOG.error("failed to create event {} with data {}", eventClass, json, e);
		}

		return null;
	}

	public static IEvent createEvent(String eventClass, IDataContainer data, DatabaseHandle databaseHandle,
		IDaoProvider daoProvider, ViewProvider viewProvider) {
		if (eventClass.equals("com.anfelisa.todo.events.CreateTodoSuccessEvent")) {
			return new CreateTodoSuccessEvent((TodoData)data, databaseHandle, daoProvider, viewProvider);
		}
		if (eventClass.equals("com.anfelisa.todo.events.ToggleTodoSuccessEvent")) {
			return new ToggleTodoSuccessEvent((TodoToggleData)data, databaseHandle, daoProvider, viewProvider);
		}
		if (eventClass.equals("com.anfelisa.todo.events.ToggleAllSuccessEvent")) {
			return new ToggleAllSuccessEvent((ToggleAllData)data, databaseHandle, daoProvider, viewProvider);
		}
		if (eventClass.equals("com.anfelisa.todo.events.UpdateTodoSuccessEvent")) {
			return new UpdateTodoSuccessEvent((TodoData)data, databaseHandle, daoProvider, viewProvider);
		}
		if (eventClass.equals("com.anfelisa.todo.events.DeleteTodoSuccessEvent")) {
			return new DeleteTodoSuccessEvent((TodoIdData)data, databaseHandle, daoProvider, viewProvider);
		}
		if (eventClass.equals("com.anfelisa.todo.events.ClearDoneSuccessEvent")) {
			return new ClearDoneSuccessEvent((ClearDoneData)data, databaseHandle, daoProvider, viewProvider);
		}

		return null;
	}
}

