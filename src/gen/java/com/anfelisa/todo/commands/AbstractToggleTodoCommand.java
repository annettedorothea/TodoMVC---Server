package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;
import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.ITodoToggleData;

public abstract class AbstractToggleTodoCommand extends Command<ITodoToggleData> {

	protected static final String success = "success";

	public AbstractToggleTodoCommand(ITodoToggleData commandParam, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.commands.ToggleTodoCommand", commandParam, daoProvider, viewProvider);
	}

	public AbstractToggleTodoCommand(IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.commands.ToggleTodoCommand", null, daoProvider, viewProvider);
	}

	@Override
	public void publishEvents(Handle handle, Handle timelineHandle) {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.ToggleTodoSuccessEvent(this.commandData, daoProvider, viewProvider).publish(handle, timelineHandle);
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
}

/*       S.D.G.       */
