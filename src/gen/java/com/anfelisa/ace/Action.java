package com.anfelisa.ace;

import java.lang.reflect.Constructor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.jdbi.v3.core.Jdbi;

import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Action<T extends IDataContainer> implements IAction {

	static final Logger LOG = LoggerFactory.getLogger(Action.class);

	protected T actionData;
	private String actionName;
	private HttpMethod httpMethod;
	protected DatabaseHandle databaseHandle;
	private Jdbi jdbi;
	protected JodaObjectMapper mapper;
	protected CustomAppConfiguration appConfiguration;
	protected IDaoProvider daoProvider;
	protected ViewProvider viewProvider;

	public Action(String actionName, HttpMethod httpMethod, Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super();
		this.actionName = actionName;
		this.httpMethod = httpMethod;
		this.jdbi = jdbi;
		mapper = new JodaObjectMapper();
		this.appConfiguration = appConfiguration;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
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
		databaseHandle = new DatabaseHandle(jdbi.open(), jdbi.open());
		databaseHandle.beginTransaction();
		try {
			if (!ServerConfiguration.REPLAY.equals(appConfiguration.getServerConfiguration().getMode())) {
				this.actionData.setSystemTime(new DateTime());
			} else {
				ITimelineItem timelineItem = E2E.selectAction(this.actionData.getUuid());
				if (timelineItem != null) {
					Class<?> cl = Class.forName(timelineItem.getName());
					Constructor<?> con = cl.getConstructor(Jdbi.class, CustomAppConfiguration.class, IDaoProvider.class, ViewProvider.class);
					IAction action = (IAction) con.newInstance(jdbi, appConfiguration, daoProvider, viewProvider);
					action.initActionData(timelineItem.getData());
					this.actionData.setSystemTime(action.getActionData().getSystemTime());
				} else {
					this.actionData.setSystemTime(new DateTime());
				}
			}
			daoProvider.addActionToTimeline(this);
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
				return Response.ok(this.getActionData().toPresentationalData()).build();
			} else if (httpMethod == HttpMethod.POST) {
				return Response.ok(this.getActionData().getUuid()).build();
			} else {
				return Response.ok().build();
			}
		} catch (WebApplicationException x) {
			daoProvider.addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle);
			databaseHandle.rollbackTransaction();
			LOG.error(actionName + " failed " + x.getMessage());
			x.printStackTrace();
			App.reportException(x);
			return Response.status(x.getResponse().getStatusInfo()).entity(x.getMessage()).build();
		} catch (Exception x) {
			daoProvider.addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle);
			databaseHandle.rollbackTransaction();
			LOG.error(actionName + " failed " + x.getMessage());
			x.printStackTrace();
			App.reportException(x);
			return Response.status(500).entity(x.getMessage()).build();
		} finally {
			databaseHandle.close();
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
