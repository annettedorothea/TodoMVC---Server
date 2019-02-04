package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;
import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.IToggleAllData;

public abstract class AbstractToggleAllCommand extends Command<IToggleAllData> {

	protected static final String success = "success";

	public AbstractToggleAllCommand(IToggleAllData commandParam, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.commands.ToggleAllCommand", commandParam, daoProvider, viewProvider);
	}

	public AbstractToggleAllCommand(IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.commands.ToggleAllCommand", null, daoProvider, viewProvider);
	}

	@Override
	public void publishEvents(Handle handle, Handle timelineHandle) {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.ToggleAllSuccessEvent(this.commandData, daoProvider, viewProvider).publish(handle, timelineHandle);
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
}

/*       S.D.G.       */
