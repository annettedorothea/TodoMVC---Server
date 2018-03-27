package com.anfelisa.todo.events;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.ToggleAllData;

public abstract class AbstractToggleAllSuccessEvent extends Event<ToggleAllData> {

	public AbstractToggleAllSuccessEvent(ToggleAllData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleAllSuccessEvent", eventParam, databaseHandle);
	}
	
	public AbstractToggleAllSuccessEvent(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleAllSuccessEvent", null, databaseHandle);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, ToggleAllData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	

}

/*       S.D.G.       */
