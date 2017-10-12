package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoIdData;

public abstract class AbstractDeleteTodoEvent extends Event<TodoIdData> {

	public AbstractDeleteTodoEvent(TodoIdData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.DeleteTodoEvent", eventParam, databaseHandle);
	}

}

/*       S.D.G.       */
