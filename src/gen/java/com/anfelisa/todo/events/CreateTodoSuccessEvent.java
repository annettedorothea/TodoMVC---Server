package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.TodoData;

public class CreateTodoSuccessEvent extends Event<TodoData> {

	public CreateTodoSuccessEvent(TodoData eventData, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.CreateTodoSuccessEvent", eventData, databaseHandle, daoProvider, viewProvider);
	}
	
	public CreateTodoSuccessEvent(DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.CreateTodoSuccessEvent", null, databaseHandle, daoProvider, viewProvider);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, TodoData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */