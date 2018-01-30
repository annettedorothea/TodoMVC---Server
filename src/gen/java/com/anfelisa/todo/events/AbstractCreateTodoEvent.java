package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoData;

public abstract class AbstractCreateTodoEvent extends Event<TodoData> {

	public AbstractCreateTodoEvent(TodoData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.CreateTodoEvent", eventParam, databaseHandle);
	}
	
	public AbstractCreateTodoEvent(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.CreateTodoEvent", null, databaseHandle);
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
