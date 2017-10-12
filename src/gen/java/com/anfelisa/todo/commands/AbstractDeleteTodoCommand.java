package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.DatabaseHandle;

import com.anfelisa.todo.data.TodoIdData;

public abstract class AbstractDeleteTodoCommand extends Command<TodoIdData> {

	protected static final String success = "success";

	public AbstractDeleteTodoCommand(TodoIdData commandParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.DeleteTodoCommand", commandParam, databaseHandle);
	}

	@Override
	protected void publishEvents() {
		switch (this.outcome) {
		case success:
			new com.anfelisa.todo.events.DeleteTodoEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + outcome);
		}
	}

}

/*       S.D.G.       */
