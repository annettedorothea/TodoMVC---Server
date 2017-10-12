package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.ClearDoneData;

public abstract class AbstractClearDoneEvent extends Event<ClearDoneData> {

	public AbstractClearDoneEvent(ClearDoneData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ClearDoneEvent", eventParam, databaseHandle);
	}

}

/*       S.D.G.       */
