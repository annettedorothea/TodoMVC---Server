package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.todo.data.ToggleAllData;

public abstract class AbstractToggleAllEvent extends Event<ToggleAllData> {

	public AbstractToggleAllEvent(ToggleAllData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleAllEvent", eventParam, databaseHandle);
	}
	
	public AbstractToggleAllEvent(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleAllEvent", null, databaseHandle);
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
