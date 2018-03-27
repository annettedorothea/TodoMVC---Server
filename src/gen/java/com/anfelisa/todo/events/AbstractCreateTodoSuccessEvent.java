package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoData;

public abstract class AbstractCreateTodoSuccessEvent extends Event<TodoData> {

	public AbstractCreateTodoSuccessEvent(TodoData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.CreateTodoSuccessEvent", eventParam, databaseHandle);
	}
	
	public AbstractCreateTodoSuccessEvent(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.CreateTodoSuccessEvent", null, databaseHandle);
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
