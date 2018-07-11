package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.TodoIdData;

public abstract class AbstractDeleteTodoSuccessEvent extends Event<TodoIdData> {

	public AbstractDeleteTodoSuccessEvent(TodoIdData eventParam, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.DeleteTodoSuccessEvent", eventParam, databaseHandle, daoProvider, viewProvider);
	}
	
	public AbstractDeleteTodoSuccessEvent(DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.DeleteTodoSuccessEvent", null, databaseHandle, daoProvider, viewProvider);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, TodoIdData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	protected String[] getNotifiedListeners() {
		return new String[] { "com.anfelisa.todo.views.TodoView.delete" };
	}
	
	

}

/*       S.D.G.       */
