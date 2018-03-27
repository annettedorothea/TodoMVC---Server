package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoIdData;

public class DeleteTodoSuccessEvent extends AbstractDeleteTodoSuccessEvent {

	static final Logger LOG = LoggerFactory.getLogger(DeleteTodoSuccessEvent.class);

	public DeleteTodoSuccessEvent(TodoIdData eventParam, DatabaseHandle databaseHandle) {
		super(eventParam, databaseHandle);
	}

	public DeleteTodoSuccessEvent(DatabaseHandle databaseHandle) {
		this(null, databaseHandle);
	}

	@Override
	protected void prepareDataForView() {
		this.eventData = this.eventParam;
	}

}

/*       S.D.G.       */
