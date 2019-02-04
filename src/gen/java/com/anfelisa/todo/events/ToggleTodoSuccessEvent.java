package com.anfelisa.todo.events;

import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.ITodoToggleData;

public class ToggleTodoSuccessEvent extends Event<ITodoToggleData> {

	public ToggleTodoSuccessEvent(ITodoToggleData eventData, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ToggleTodoSuccessEvent", eventData, daoProvider, viewProvider);
	}

}

/*       S.D.G.       */
