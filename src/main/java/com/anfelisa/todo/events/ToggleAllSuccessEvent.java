package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.ToggleAllData;

public class ToggleAllSuccessEvent extends AbstractToggleAllSuccessEvent {

	static final Logger LOG = LoggerFactory.getLogger(ToggleAllSuccessEvent.class);

	public ToggleAllSuccessEvent(ToggleAllData eventParam, DatabaseHandle databaseHandle) {
		super(eventParam, databaseHandle);
	}

	public ToggleAllSuccessEvent(DatabaseHandle databaseHandle) {
		this(null, databaseHandle);
	}

	@Override
	protected void prepareDataForView() {
		this.eventData = this.eventParam;
	}

}

/*       S.D.G.       */
