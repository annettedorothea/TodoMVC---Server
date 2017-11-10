package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.ToggleAllData;

public class ToggleAllEvent extends AbstractToggleAllEvent {

	static final Logger LOG = LoggerFactory.getLogger(ToggleAllEvent.class);

	public ToggleAllEvent(ToggleAllData eventParam, DatabaseHandle databaseHandle) {
		super(eventParam, databaseHandle);
	}

	public ToggleAllEvent(DatabaseHandle databaseHandle) {
		this(null, databaseHandle);
	}

	@Override
	protected void prepareDataForView() {
		this.eventData = this.eventParam;
	}

}

/*       S.D.G.       */
