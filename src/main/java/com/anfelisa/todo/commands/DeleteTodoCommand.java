package com.anfelisa.todo.commands;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoIdData;

public class DeleteTodoCommand extends AbstractDeleteTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(DeleteTodoCommand.class);

	public DeleteTodoCommand(TodoIdData commandParam, DatabaseHandle databaseHandle) {
		super(commandParam, databaseHandle);
	}

	public DeleteTodoCommand(DatabaseHandle databaseHandle) {
		super(null, databaseHandle);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
