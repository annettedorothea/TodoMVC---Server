package com.anfelisa.todo.events;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.Event;

import com.anfelisa.todo.data.TodoToggleData;

public abstract class AbstractToggleTodoEvent extends Event<TodoToggleData> {

	public AbstractToggleTodoEvent(TodoToggleData eventParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.events.ToggleTodoEvent", eventParam, databaseHandle);
	}

}

/*       S.D.G.       */
