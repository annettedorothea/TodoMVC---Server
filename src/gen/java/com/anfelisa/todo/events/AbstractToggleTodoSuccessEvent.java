package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.TodoToggleData;

public abstract class AbstractToggleTodoSuccessEvent extends Event<TodoToggleData> {

	public AbstractToggleTodoSuccessEvent(TodoToggleData eventParam, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ToggleTodoSuccessEvent", eventParam, databaseHandle, daoProvider, viewProvider);
	}
	
	public AbstractToggleTodoSuccessEvent(DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ToggleTodoSuccessEvent", null, databaseHandle, daoProvider, viewProvider);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, TodoToggleData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	protected String[] getNotifiedListeners() {
		return new String[] { "com.anfelisa.todo.views.TodoView.toggle" };
	}
	
	

}

/*       S.D.G.       */
