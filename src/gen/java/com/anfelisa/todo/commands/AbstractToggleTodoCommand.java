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

	public AbstractToggleTodoCommand(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.ToggleTodoCommand", null, databaseHandle);
	}

	@Override
	public void publishEvents() {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.ToggleTodoSuccessEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
	public void initCommandData(String json) {
		try {
			this.commandData = mapper.readValue(json, TodoToggleData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
