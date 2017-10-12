package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.DatabaseHandle;

import com.anfelisa.todo.data.TodoToggleData;

public abstract class AbstractToggleTodoCommand extends Command<TodoToggleData> {

	protected static final String success = "success";

	public AbstractToggleTodoCommand(TodoToggleData commandParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.ToggleTodoCommand", commandParam, databaseHandle);
	}

	@Override
	protected void publishEvents() {
		switch (this.outcome) {
		case success:
			new com.anfelisa.todo.events.ToggleTodoEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + outcome);
		}
	}

}

/*       S.D.G.       */
