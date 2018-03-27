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

	public AbstractToggleAllCommand(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.ToggleAllCommand", null, databaseHandle);
	}

	@Override
	public void publishEvents() {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.ToggleAllSuccessEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
	public void initCommandData(String json) {
		try {
			this.commandData = mapper.readValue(json, ToggleAllData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
