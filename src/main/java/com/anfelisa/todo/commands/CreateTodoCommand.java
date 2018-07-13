package com.anfelisa.todo.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.TodoData;

public class CreateTodoCommand extends AbstractCreateTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(CreateTodoCommand.class);

	public CreateTodoCommand(TodoData commandData, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(commandData, databaseHandle, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setDone(false);
		this.commandData.setCreatedDateTime(this.commandData.getSystemTime());
		this.commandData.setId(this.commandData.getUuid());
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
