package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.DatabaseHandle;

import com.anfelisa.todo.data.TodoListData;

public abstract class AbstractGetAllTodosCommand extends Command<TodoListData> {


	public AbstractGetAllTodosCommand(TodoListData commandParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.GetAllTodosCommand", commandParam, databaseHandle);
	}

	public AbstractGetAllTodosCommand(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.GetAllTodosCommand", null, databaseHandle);
	}

	@Override
	public void publishEvents() {
		switch (this.commandData.getOutcome()) {
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
	public void initCommandData(String json) {
		try {
			this.commandData = mapper.readValue(json, TodoListData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
