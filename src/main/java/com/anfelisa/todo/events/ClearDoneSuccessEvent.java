package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.ClearDoneData;

public class ClearDoneSuccessEvent extends AbstractClearDoneSuccessEvent {

	static final Logger LOG = LoggerFactory.getLogger(ClearDoneSuccessEvent.class);

	public ClearDoneSuccessEvent(ClearDoneData eventParam, DatabaseHandle databaseHandle) {
		super(eventParam, databaseHandle);
	}

	public ClearDoneSuccessEvent(DatabaseHandle databaseHandle) {
		this(null, databaseHandle);
	}

	@Override
	protected void prepareDataForView() {
		this.eventData = this.eventParam;
	}

}

/*       S.D.G.       */
