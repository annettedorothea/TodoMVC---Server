package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.todo.data.TodoData;

public abstract class AbstractUpdateTodoEvent extends Event<TodoData> {

	public AbstractUpdateTodoEvent(TodoData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.UpdateTodoEvent", eventParam, databaseHandle);
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
