package com.anfelisa.ace;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public abstract class Action<T extends IDataContainer> implements IAction {

	protected T actionData;
	protected String actionName;
	private HttpMethod httpMethod;

	public Action(String actionName, HttpMethod httpMethod) {
		super();
		this.actionName = actionName;
		this.httpMethod = httpMethod;
	}

	public String getActionName() {
		return this.actionName;
	}

	public HttpMethod getHttpMethod() {
		return this.httpMethod;
	}

	public IDataContainer getActionData() {
		return this.actionData;
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
	
	protected void throwServiceUnavailable(String error) {
		throw new WebApplicationException(error, Response.Status.SERVICE_UNAVAILABLE);
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

	protected Object createReponse() {
		return this.actionData.getUuid();
	}

}
