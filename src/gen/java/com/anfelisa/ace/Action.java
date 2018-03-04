package com.anfelisa.ace;

import java.lang.reflect.Constructor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Action<T extends IDataContainer> implements IAction {

	static final Logger LOG = LoggerFactory.getLogger(Action.class);

	protected T actionData;
	private String actionName;
	private HttpMethod httpMethod;
	protected DatabaseHandle databaseHandle;
	private DBI jdbi;
	protected JodaObjectMapper mapper;

	public Action(String actionName, HttpMethod httpMethod, DBI jdbi) {
		super();
		this.actionName = actionName;
		this.httpMethod = httpMethod;
		this.jdbi = jdbi;
		mapper = new JodaObjectMapper();
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

	protected abstract void loadDataForGetRequest();

	public Response apply() {
		this.databaseHandle = new DatabaseHandle(jdbi.open(), jdbi.open());
		Handle timelineHandle = null;
		databaseHandle.beginTransaction();
		try {
			if (AceController.getAceExecutionMode() != AceExecutionMode.REPLAY) {
				this.actionData.setSystemTime(new DateTime());
			} else {
				ITimelineItem timelineItem = E2E.selectAction(this.actionData.getUuid());
				if (timelineItem != null) {
					Class<?> cl = Class.forName(timelineItem.getName());
					Constructor<?> con = cl.getConstructor(DBI.class);
					IAction action = (IAction) con.newInstance(jdbi);
					action.initActionData(timelineItem.getData());
					this.actionData.setSystemTime(action.getActionData().getSystemTime());
				} else {
					this.actionData.setSystemTime(new DateTime());
				}
			}
			AceController.addActionToTimeline(this);
			if (httpMethod != HttpMethod.GET) {
				ICommand command = this.getCommand();
				if (command != null) {
					command.execute();
				} else {
					throw new WebApplicationException(actionName + " returns no command");
				}
			} else {
				this.loadDataForGetRequest();
			}
			databaseHandle.commitTransaction();
			if (httpMethod == HttpMethod.GET) {
				return Response.ok(this.getActionData()).build();
			} else if (httpMethod == HttpMethod.POST) {
				return Response.ok(this.getActionData().getCreatedId()).build();
			} else {
				return Response.ok().build();
			}
		} catch (WebApplicationException x) {
			AceController.addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle);
			databaseHandle.rollbackTransaction();
			LOG.error(actionName + " failed " + x.getMessage());
			//x.printStackTrace();
			return Response.status(x.getResponse().getStatusInfo()).entity(x.getMessage()).build();
		} catch (Exception x) {
			AceController.addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle);
			databaseHandle.rollbackTransaction();
			LOG.error(actionName + " failed " + x.getMessage());
			//x.printStackTrace();
			return Response.status(500).entity(x.getMessage()).build();
		} finally {
			databaseHandle.close();
			if (timelineHandle != null) {
				timelineHandle.close();
			}
		}
	}

	public DatabaseHandle getDatabaseHandle() {
		return databaseHandle;
	}

	public void setDatabaseHandle(DatabaseHandle databaseHandle) {
		this.databaseHandle = databaseHandle;
	}

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

}
