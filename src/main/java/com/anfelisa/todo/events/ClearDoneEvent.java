package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.ClearDoneData;

public class ClearDoneEvent extends AbstractClearDoneEvent {

	static final Logger LOG = LoggerFactory.getLogger(ClearDoneEvent.class);

	public ClearDoneEvent(ClearDoneData eventParam, DatabaseHandle databaseHandle) {
		super(eventParam, databaseHandle);
	}

	public ClearDoneEvent(DatabaseHandle databaseHandle) {
		this(null, databaseHandle);
	}

	@Override
	protected void prepareDataForView() {
		this.eventData = this.eventParam;
	}

}

/*       S.D.G.       */
