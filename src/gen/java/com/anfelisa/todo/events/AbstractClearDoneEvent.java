package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;
import javax.ws.rs.WebApplicationException;

import com.anfelisa.todo.data.ClearDoneData;

public abstract class AbstractClearDoneEvent extends Event<ClearDoneData> {

	public AbstractClearDoneEvent(ClearDoneData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ClearDoneEvent", eventParam, databaseHandle);
	}
	
	public void initEventData(String json) {
		try {
			this.eventData = mapper.readValue(json, ClearDoneData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	

}

/*       S.D.G.       */
