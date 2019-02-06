package com.anfelisa.ace;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import com.anfelisa.ace.CustomAppConfiguration;

import org.jdbi.v3.core.Handle;

public abstract class Command<T extends IDataContainer> implements ICommand {

	protected T commandData;
	private String commandName;
	protected JodaObjectMapper mapper;
	protected IDaoProvider daoProvider;
	protected ViewProvider viewProvider;
	protected CustomAppConfiguration appConfiguration;

	public Command(String commandName, T commandData, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super();
		this.commandData = commandData;
		this.commandName = commandName;
		mapper = new JodaObjectMapper();
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
		this.appConfiguration = appConfiguration;
	}

	protected abstract void executeCommand(Handle readonlyHandle);

	public void execute(Handle readonlyHandle, Handle timelineHandle) {
		this.executeCommand(readonlyHandle);
		daoProvider.getAceDao().addCommandToTimeline(this, timelineHandle);
	}

	public IDataContainer getCommandData() {
		return commandData;
	}
	
	@SuppressWarnings("unchecked")
	public void setCommandData(IDataContainer data) {
		commandData = (T)data;
	}

	public String getCommandName() {
		return commandName;
	}

	protected void throwUnauthorized() {
		throw new WebApplicationException(Response.Status.UNAUTHORIZED);
	}

	protected void throwBadRequest() {
		throw new WebApplicationException(Response.Status.BAD_REQUEST);
	}

	protected void throwBadRequest(String error) {
		throw new WebApplicationException(error, Response.Status.BAD_REQUEST);
	}

	protected void throwForbidden(String error) {
		throw new WebApplicationException(error, Response.Status.FORBIDDEN);
	}

	protected void throwInternalServerError(Exception x) {
		String message = x.getMessage();
		StackTraceElement[] stackTrace = x.getStackTrace();
		int i = 1;
		for (StackTraceElement stackTraceElement : stackTrace) {
			message += "\n" + stackTraceElement.toString();
			if (i > 3) {
				message += "\n" + (stackTrace.length - 4) + " more...";
				break;
			}
			i++;
		}
		throw new WebApplicationException(message, Response.Status.INTERNAL_SERVER_ERROR);
	}

}

