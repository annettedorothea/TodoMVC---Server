package com.anfelisa.todo.commands;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.ClearDoneData;

public class ClearDoneCommand extends AbstractClearDoneCommand {

	static final Logger LOG = LoggerFactory.getLogger(ClearDoneCommand.class);

	public ClearDoneCommand(ClearDoneData commandParam, DatabaseHandle databaseHandle) {
		super(commandParam, databaseHandle);
	}

	public ClearDoneCommand(DatabaseHandle databaseHandle) {
		super(null, databaseHandle);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
