package com.anfelisa.todo.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.ClearDoneData;

public class ClearDoneCommand extends AbstractClearDoneCommand {

	static final Logger LOG = LoggerFactory.getLogger(ClearDoneCommand.class);

	public ClearDoneCommand(ClearDoneData commandData, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(commandData, databaseHandle, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
