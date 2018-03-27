package com.anfelisa.todo.commands;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoListData;

public class GetAllTodosCommand extends AbstractGetAllTodosCommand {

	static final Logger LOG = LoggerFactory.getLogger(GetAllTodosCommand.class);

	public GetAllTodosCommand(TodoListData commandParam, DatabaseHandle databaseHandle) {
		super(commandParam, databaseHandle);
	}

	public GetAllTodosCommand(DatabaseHandle databaseHandle) {
		super(null, databaseHandle);
	}

	@Override
	protected void executeCommand() {
	}

}

/*       S.D.G.       */
