package com.anfelisa.todo.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.TodoIdData;

public class DeleteTodoCommand extends AbstractDeleteTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(DeleteTodoCommand.class);

	public DeleteTodoCommand(TodoIdData commandData, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(commandData, databaseHandle, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
