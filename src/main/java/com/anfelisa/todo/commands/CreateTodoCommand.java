package com.anfelisa.todo.commands;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoData;

public class CreateTodoCommand extends AbstractCreateTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(CreateTodoCommand.class);

	public CreateTodoCommand(TodoData commandParam, DatabaseHandle databaseHandle) {
		super(commandParam, databaseHandle);
	}

	public CreateTodoCommand(DatabaseHandle databaseHandle) {
		super(null, databaseHandle);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setDone(false);
		this.commandData.setCreatedDateTime(this.commandData.getSystemTime());
		this.commandData.setDescription(this.commandData.getDescription());
		this.commandData.setId(this.commandData.getUuid());
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
