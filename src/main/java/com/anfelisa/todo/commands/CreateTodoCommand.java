package com.anfelisa.todo.commands;

import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.ITodoData;

public class CreateTodoCommand extends AbstractCreateTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(CreateTodoCommand.class);

	public CreateTodoCommand(ITodoData actionData, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(actionData, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand(Handle readonlyHandle) {
		this.commandData.setDone(false);
		this.commandData.setCreatedDateTime(this.commandData.getSystemTime());
		this.commandData.setId(this.commandData.getUuid());
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
