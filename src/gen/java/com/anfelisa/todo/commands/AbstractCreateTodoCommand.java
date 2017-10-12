package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.DatabaseHandle;

import com.anfelisa.todo.data.TodoData;

public abstract class AbstractCreateTodoCommand extends Command<TodoData> {

	protected static final String success = "success";

	public AbstractCreateTodoCommand(TodoData commandParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.CreateTodoCommand", commandParam, databaseHandle);
	}

	@Override
	protected void publishEvents() {
		switch (this.outcome) {
		case success:
			new com.anfelisa.todo.events.CreateTodoEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + outcome);
		}
	}

}

/*       S.D.G.       */
