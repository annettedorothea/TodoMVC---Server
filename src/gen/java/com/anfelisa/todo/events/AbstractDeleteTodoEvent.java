package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.todo.data.TodoIdData;

public abstract class AbstractDeleteTodoEvent extends Event<TodoIdData> {

	public AbstractDeleteTodoEvent(TodoIdData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.DeleteTodoEvent", eventParam, databaseHandle);
	}
	
	public AbstractDeleteTodoEvent(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.DeleteTodoEvent", null, databaseHandle);
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
