package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.DatabaseHandle;

import com.anfelisa.todo.data.TodoData;

public abstract class AbstractUpdateTodoCommand extends Command<TodoData> {

	protected static final String success = "success";

	public AbstractUpdateTodoCommand(TodoData commandParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.UpdateTodoCommand", commandParam, databaseHandle);
	}

	public AbstractUpdateTodoCommand(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.UpdateTodoCommand", null, databaseHandle);
	}

	@Override
	public void publishEvents() {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.UpdateTodoEvent(this.commandData, databaseHandle).publish();
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
