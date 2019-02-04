package com.anfelisa.todo.events;

import com.anfelisa.ace.Event;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.IClearDoneData;

public class ClearDoneSuccessEvent extends Event<IClearDoneData> {

	public ClearDoneSuccessEvent(IClearDoneData eventData, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.events.ClearDoneSuccessEvent", eventData, daoProvider, viewProvider);
	}

}

/*       S.D.G.       */
