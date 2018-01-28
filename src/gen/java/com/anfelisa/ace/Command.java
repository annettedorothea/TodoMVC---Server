package com.anfelisa.ace;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.Handle;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Command<T extends IDataContainer> implements ICommand {

	protected T commandData;
	private String commandName;
	@JsonIgnore
	protected DatabaseHandle databaseHandle;
	protected JodaObjectMapper mapper;

	public Command(String commandName, T commandData, DatabaseHandle databaseHandle) {
		super();
		this.commandData = commandData;
		this.commandName = commandName;
		this.databaseHandle = databaseHandle;
		mapper = new JodaObjectMapper();
	}

	protected void executeCommand() {
	}

	public void execute() {
		this.executeCommand();
		if (AceController.getAceExecutionMode() != AceExecutionMode.MIGRATE) {
			AceController.addCommandToTimeline(this);
		}
		this.publishEvents();
	}

	public IDataContainer getCommandData() {
		return commandData;
	}

	public String getCommandName() {
		return commandName;
	}

	@JsonIgnore
	public DatabaseHandle getDatabaseHandle() {
		return databaseHandle;
	}

	@JsonIgnore
	protected Handle getHandle() {
		if (databaseHandle != null) {
			return databaseHandle.getHandle();
		} else {
			throw new RuntimeException("no database handle");
		}
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

