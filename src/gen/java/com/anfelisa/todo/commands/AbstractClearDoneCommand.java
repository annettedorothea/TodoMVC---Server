package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.DatabaseHandle;

import com.anfelisa.todo.data.ClearDoneData;

public abstract class AbstractClearDoneCommand extends Command<ClearDoneData> {

	protected static final String success = "success";

	public AbstractClearDoneCommand(ClearDoneData commandParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.ClearDoneCommand", commandParam, databaseHandle);
	}

	@Override
	protected void publishEvents() {
		switch (this.outcome) {
		case success:
			new com.anfelisa.todo.events.ClearDoneEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + outcome);
		}
	}

}

/*       S.D.G.       */
