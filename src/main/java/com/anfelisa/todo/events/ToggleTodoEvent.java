package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoToggleData;

public class ToggleTodoEvent extends AbstractToggleTodoEvent {

	static final Logger LOG = LoggerFactory.getLogger(ToggleTodoEvent.class);

	public ToggleTodoEvent(TodoToggleData eventParam, DatabaseHandle databaseHandle) {
		super(eventParam, databaseHandle);
	}

	@Override
	protected void prepareDataForView() {
		this.eventData = this.eventParam;
	}

}

/*       S.D.G.       */
