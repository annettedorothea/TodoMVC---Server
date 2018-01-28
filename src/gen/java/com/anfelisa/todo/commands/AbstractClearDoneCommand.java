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

	public AbstractClearDoneCommand(DatabaseHandle databaseHandle) {
		super("com.anfelisa.todo.commands.ClearDoneCommand", null, databaseHandle);
	}

	@Override
	public void publishEvents() {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.ClearDoneEvent(this.commandData, databaseHandle).publish();
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
	public void initCommandData(String json) {
		try {
			this.commandData = mapper.readValue(json, ClearDoneData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}

/*       S.D.G.       */
