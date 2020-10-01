/********************************************************************************
 * generated by de.acegen 0.9.10
 ********************************************************************************/




package com.anfelisa.todo.events;

import de.acegen.IDaoProvider;
import de.acegen.IEvent;
import de.acegen.ViewProvider;
import com.anfelisa.todo.data.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.acegen.IDataContainer;
import de.acegen.CustomAppConfiguration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class EventFactory {
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static final Logger LOG = LoggerFactory.getLogger(EventFactory.class);

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static IEvent createEvent(String eventClass, String json, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		try {
			if (eventClass.equals("com.anfelisa.todo.events.CreateTodoSuccessEvent")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				CreateTodoSuccessEvent event = new CreateTodoSuccessEvent(data, daoProvider, viewProvider, appConfiguration);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ToggleTodoSuccessEvent")) {
				TodoToggleData data = mapper.readValue(json, TodoToggleData.class);
				data.migrateLegacyData(json);
				ToggleTodoSuccessEvent event = new ToggleTodoSuccessEvent(data, daoProvider, viewProvider, appConfiguration);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ToggleAllSuccessEvent")) {
				ToggleAllData data = mapper.readValue(json, ToggleAllData.class);
				data.migrateLegacyData(json);
				ToggleAllSuccessEvent event = new ToggleAllSuccessEvent(data, daoProvider, viewProvider, appConfiguration);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.UpdateTodoSuccessEvent")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				UpdateTodoSuccessEvent event = new UpdateTodoSuccessEvent(data, daoProvider, viewProvider, appConfiguration);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.DeleteTodoSuccessEvent")) {
				TodoIdData data = mapper.readValue(json, TodoIdData.class);
				data.migrateLegacyData(json);
				DeleteTodoSuccessEvent event = new DeleteTodoSuccessEvent(data, daoProvider, viewProvider, appConfiguration);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ClearDoneSuccessEvent")) {
				ClearDoneData data = mapper.readValue(json, ClearDoneData.class);
				data.migrateLegacyData(json);
				ClearDoneSuccessEvent event = new ClearDoneSuccessEvent(data, daoProvider, viewProvider, appConfiguration);
				return event;
			}
		} catch (IOException e) {
			LOG.error("failed to create event {} with data {}", eventClass, json, e);
		}

		return null;
	}

	public static IEvent createEvent(String eventClass, IDataContainer data, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {


		if (eventClass.equals("com.anfelisa.todo.events.CreateTodoSuccessEvent")) {
			return new CreateTodoSuccessEvent((TodoData)data, daoProvider, viewProvider, appConfiguration);
		}

		if (eventClass.equals("com.anfelisa.todo.events.ToggleTodoSuccessEvent")) {
			return new ToggleTodoSuccessEvent((TodoToggleData)data, daoProvider, viewProvider, appConfiguration);
		}

		if (eventClass.equals("com.anfelisa.todo.events.ToggleAllSuccessEvent")) {
			return new ToggleAllSuccessEvent((ToggleAllData)data, daoProvider, viewProvider, appConfiguration);
		}

		if (eventClass.equals("com.anfelisa.todo.events.UpdateTodoSuccessEvent")) {
			return new UpdateTodoSuccessEvent((TodoData)data, daoProvider, viewProvider, appConfiguration);
		}

		if (eventClass.equals("com.anfelisa.todo.events.DeleteTodoSuccessEvent")) {
			return new DeleteTodoSuccessEvent((TodoIdData)data, daoProvider, viewProvider, appConfiguration);
		}

		if (eventClass.equals("com.anfelisa.todo.events.ClearDoneSuccessEvent")) {
			return new ClearDoneSuccessEvent((ClearDoneData)data, daoProvider, viewProvider, appConfiguration);
		}


		return null;
	}
}



/******* S.D.G. *******/



