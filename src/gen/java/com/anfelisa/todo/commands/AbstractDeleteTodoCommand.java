package com.anfelisa.todo.commands;

import javax.ws.rs.WebApplicationException;
import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.Command;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

import com.anfelisa.todo.data.ITodoIdData;

public abstract class AbstractDeleteTodoCommand extends Command<ITodoIdData> {

	protected static final String success = "success";

	public AbstractDeleteTodoCommand(ITodoIdData commandParam, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.commands.DeleteTodoCommand", commandParam, daoProvider, viewProvider);
	}

	public AbstractDeleteTodoCommand(IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.commands.DeleteTodoCommand", null, daoProvider, viewProvider);
	}

	@Override
	public void publishEvents(Handle handle, Handle timelineHandle) {
		switch (this.commandData.getOutcome()) {
		case success:
			new com.anfelisa.todo.events.DeleteTodoSuccessEvent(this.commandData, daoProvider, viewProvider).publish(handle, timelineHandle);
			break;
		default:
			throw new WebApplicationException("unhandled outcome " + this.commandData.getOutcome());
		}
	}
	
}

/*       S.D.G.       */
