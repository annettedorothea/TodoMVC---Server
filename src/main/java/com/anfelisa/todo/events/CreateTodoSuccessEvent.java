package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoData;

public class CreateTodoSuccessEvent extends AbstractCreateTodoSuccessEvent {

	static final Logger LOG = LoggerFactory.getLogger(CreateTodoSuccessEvent.class);

	public CreateTodoSuccessEvent(TodoData eventParam, DatabaseHandle databaseHandle) {
		super(eventParam, databaseHandle);
	}

	public CreateTodoSuccessEvent(DatabaseHandle databaseHandle) {
		this(null, databaseHandle);
	}

	@Override
	protected void prepareDataForView() {
		this.eventData = this.eventParam;
	}

}

/*       S.D.G.       */
