package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoToggleData;

public class ToggleTodoSuccessEvent extends AbstractToggleTodoSuccessEvent {

	static final Logger LOG = LoggerFactory.getLogger(ToggleTodoSuccessEvent.class);

	public ToggleTodoSuccessEvent(TodoToggleData eventParam, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(eventParam, databaseHandle, daoProvider, viewProvider);
	}

	public ToggleTodoSuccessEvent(DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		this(null, databaseHandle, daoProvider, viewProvider);
	}

	@Override
	protected void prepareDataForView() {
		this.eventData = this.eventParam;
	}

}

/*       S.D.G.       */
