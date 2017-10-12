package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoData;

public abstract class AbstractCreateTodoEvent extends Event<TodoData> {

	public AbstractCreateTodoEvent(TodoData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.CreateTodoEvent", eventParam, databaseHandle);
	}

}

/*       S.D.G.       */
