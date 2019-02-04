package com.anfelisa.todo.events;

import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.ITodoIdData;

public class DeleteTodoSuccessEvent extends Event<ITodoIdData> {

	public DeleteTodoSuccessEvent(ITodoIdData eventData, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.DeleteTodoSuccessEvent", eventData, daoProvider, viewProvider);
	}

}

/*       S.D.G.       */
