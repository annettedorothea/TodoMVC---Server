package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.ClearDoneData;

public abstract class AbstractClearDoneSuccessEvent extends Event<ClearDoneData> {

	public AbstractClearDoneSuccessEvent(ClearDoneData eventParam, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ClearDoneSuccessEvent", eventParam, databaseHandle, daoProvider, viewProvider);
	}
	
	public AbstractClearDoneSuccessEvent(DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ClearDoneSuccessEvent", null, databaseHandle, daoProvider, viewProvider);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, ClearDoneData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	protected String[] getNotifiedListeners() {
		return new String[] { "com.anfelisa.todo.views.TodoView.clearDone" };
	}
	
	

}

/*       S.D.G.       */
