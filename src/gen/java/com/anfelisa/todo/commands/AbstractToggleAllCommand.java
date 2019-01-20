package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.ToggleAllData;

public abstract class AbstractToggleAllCommand extends Command<ToggleAllData> {

	protected static final String success = "success";

	public AbstractToggleAllCommand(ToggleAllData commandParam, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.commands.ToggleAllCommand", commandParam, databaseHandle, daoProvider, viewProvider);
	}

	public AbstractToggleAllCommand(DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.commands.ToggleAllCommand", null, databaseHandle, daoProvider, viewProvider);
	}

	@Override
	public void publishEvents() {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.ToggleAllSuccessEvent(this.commandData, databaseHandle, daoProvider, viewProvider).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
}

/*       S.D.G.       */
