package com.anfelisa.todo.events;

import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.IToggleAllData;

public class ToggleAllSuccessEvent extends Event<IToggleAllData> {

	public ToggleAllSuccessEvent(IToggleAllData eventData, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ToggleAllSuccessEvent", eventData, daoProvider, viewProvider);
	}

}

/*       S.D.G.       */
