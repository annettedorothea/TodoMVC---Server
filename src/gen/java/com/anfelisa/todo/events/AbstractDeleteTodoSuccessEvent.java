package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoIdData;

public abstract class AbstractDeleteTodoSuccessEvent extends Event<TodoIdData> {

	public AbstractDeleteTodoSuccessEvent(TodoIdData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.DeleteTodoSuccessEvent", eventParam, databaseHandle);
	}
	
	public AbstractDeleteTodoSuccessEvent(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.DeleteTodoSuccessEvent", null, databaseHandle);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, TodoIdData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	

}

/*       S.D.G.       */
