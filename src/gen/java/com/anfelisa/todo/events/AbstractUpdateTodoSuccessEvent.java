package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoData;

public abstract class AbstractUpdateTodoSuccessEvent extends Event<TodoData> {

	public AbstractUpdateTodoSuccessEvent(TodoData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.UpdateTodoSuccessEvent", eventParam, databaseHandle);
	}
	
	public AbstractUpdateTodoSuccessEvent(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.UpdateTodoSuccessEvent", null, databaseHandle);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, TodoData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	protected String[] getNotifiedListeners() {
		return new String[] { "com.anfelisa.todo.views.TodoView.update" };
	}
	
	

}

/*       S.D.G.       */
