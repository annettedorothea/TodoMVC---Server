/********************************************************************************
 * generated by de.acegen 1.0.5
 ********************************************************************************/




package com.anfelisa.todo.events;

import de.acegen.Event;
import de.acegen.IDaoProvider;
import de.acegen.ViewProvider;
import de.acegen.CustomAppConfiguration;

import com.anfelisa.todo.data.ITodoIdData;

public class DeleteTodoSuccessEvent extends Event<ITodoIdData> {

	public DeleteTodoSuccessEvent(ITodoIdData eventData, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super("com.anfelisa.todo.events.DeleteTodoSuccessEvent", eventData, daoProvider, viewProvider, appConfiguration);
	}

}



/******* S.D.G. *******/



