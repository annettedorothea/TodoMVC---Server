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

	public AbstractDeleteTodoCommand(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.DeleteTodoCommand", null, databaseHandle);
	}

	@Override
	public void publishEvents() {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.DeleteTodoEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
	public void initCommandData(String json) {
		try {
			this.commandData = mapper.readValue(json, TodoIdData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
