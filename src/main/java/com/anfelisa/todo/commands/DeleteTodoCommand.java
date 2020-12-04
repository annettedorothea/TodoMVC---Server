/********************************************************************************
 * generated by de.acegen 1.0.5
 ********************************************************************************/




package com.anfelisa.todo.commands;

import de.acegen.ViewProvider;
import de.acegen.IDaoProvider;
import de.acegen.CustomAppConfiguration;
import de.acegen.PersistenceHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.ITodoIdData;

public class DeleteTodoCommand extends AbstractDeleteTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(DeleteTodoCommand.class);

	public DeleteTodoCommand(ITodoIdData commandData, IDaoProvider daoProvider, ViewProvider viewProvider, 
			CustomAppConfiguration appConfiguration) {
		super(commandData, daoProvider, viewProvider, appConfiguration);
	}

	@Override
	protected void executeCommand(PersistenceHandle readonlyHandle) {
		this.addSuccessOutcome();
	}

}




/******* S.D.G. *******/



