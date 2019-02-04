package com.anfelisa.todo.commands;

import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.IClearDoneData;

public class ClearDoneCommand extends AbstractClearDoneCommand {

	static final Logger LOG = LoggerFactory.getLogger(ClearDoneCommand.class);

	public ClearDoneCommand(IClearDoneData commandData, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(commandData, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand(Handle readonlyHandle) {
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
