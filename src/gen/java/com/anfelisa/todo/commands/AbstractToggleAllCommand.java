package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.DatabaseHandle;

import com.anfelisa.todo.data.ToggleAllData;

public abstract class AbstractToggleAllCommand extends Command<ToggleAllData> {

	protected static final String success = "success";

	public AbstractToggleAllCommand(ToggleAllData commandParam, DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.ToggleAllCommand", commandParam, databaseHandle);
	}

	@Override
	protected void publishEvents() {
		switch (this.outcome) {
		case success:
			new com.anfelisa.todo.events.ToggleAllEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + outcome);
		}
	}

}

/*       S.D.G.       */
