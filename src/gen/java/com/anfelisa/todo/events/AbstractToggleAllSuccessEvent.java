package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.ToggleAllData;

public abstract class AbstractToggleAllSuccessEvent extends Event<ToggleAllData> {

	public AbstractToggleAllSuccessEvent(ToggleAllData eventParam, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ToggleAllSuccessEvent", eventParam, databaseHandle, daoProvider, viewProvider);
	}
	
	public AbstractToggleAllSuccessEvent(DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ToggleAllSuccessEvent", null, databaseHandle, daoProvider, viewProvider);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, ToggleAllData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	protected String[] getNotifiedListeners() {
		return new String[] { "com.anfelisa.todo.views.TodoView.toggleAll" };
	}
	
	

}

/*       S.D.G.       */
