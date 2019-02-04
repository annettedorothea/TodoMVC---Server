package com.anfelisa.todo.events;

import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.ITodoData;

public class UpdateTodoSuccessEvent extends Event<ITodoData> {

	public UpdateTodoSuccessEvent(ITodoData eventData, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.UpdateTodoSuccessEvent", eventData, daoProvider, viewProvider);
	}

}

/*       S.D.G.       */
