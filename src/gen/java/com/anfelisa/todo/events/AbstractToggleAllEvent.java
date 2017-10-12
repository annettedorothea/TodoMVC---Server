package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.ToggleAllData;

public abstract class AbstractToggleAllEvent extends Event<ToggleAllData> {

	public AbstractToggleAllEvent(ToggleAllData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleAllEvent", eventParam, databaseHandle);
	}

}

/*       S.D.G.       */
