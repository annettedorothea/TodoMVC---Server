package com.anfelisa.todo.commands;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoData;

public class UpdateTodoCommand extends AbstractUpdateTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(UpdateTodoCommand.class);

	public UpdateTodoCommand(TodoData commandParam, DatabaseHandle databaseHandle) {
		super(commandParam, databaseHandle);
	}

	@Override
	protected void executeCommand() {
		this.commandData.setUpdatedDateTime(this.commandData.getSystemTime());
		this.outcome = success;
	}

}

/*       S.D.G.       */
