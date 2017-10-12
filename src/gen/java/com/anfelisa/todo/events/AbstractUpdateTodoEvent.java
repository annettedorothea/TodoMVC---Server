package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoData;

public abstract class AbstractUpdateTodoEvent extends Event<TodoData> {

	public AbstractUpdateTodoEvent(TodoData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.UpdateTodoEvent", eventParam, databaseHandle);
	}

}

/*       S.D.G.       */
