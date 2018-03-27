package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoToggleData;

public abstract class AbstractToggleTodoSuccessEvent extends Event<TodoToggleData> {

	public AbstractToggleTodoSuccessEvent(TodoToggleData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleTodoSuccessEvent", eventParam, databaseHandle);
	}
	
	public AbstractToggleTodoSuccessEvent(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleTodoSuccessEvent", null, databaseHandle);
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
