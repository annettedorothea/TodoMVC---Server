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

	public AbstractCreateTodoCommand(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.CreateTodoCommand", null, databaseHandle);
	}

	@Override
	public void publishEvents() {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.CreateTodoEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
	public void initCommandData(String json) {
		try {
			this.commandData = mapper.readValue(json, TodoData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
