package com.anfelisa.todo.commands;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoData;

public class UpdateTodoCommand extends AbstractUpdateTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(UpdateTodoCommand.class);

	public UpdateTodoCommand(TodoData commandData, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(commandData, databaseHandle, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setUpdatedDateTime(this.commandData.getSystemTime());
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
