package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.todo.data.TodoToggleData;

public abstract class AbstractToggleTodoEvent extends Event<TodoToggleData> {

	public AbstractToggleTodoEvent(TodoToggleData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleTodoEvent", eventParam, databaseHandle);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, TodoToggleData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	

}

/*       S.D.G.       */
