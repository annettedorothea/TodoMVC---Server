package com.anfelisa.ace;

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
			IDataContainer originalData = null;
			if (!ServerConfiguration.REPLAY.equals(appConfiguration.getServerConfiguration().getMode())) {
				if (daoProvider.getAceDao().contains(databaseHandle.getHandle(), this.actionData.getUuid())) {
					databaseHandle.commitTransaction();
					return Response.status(500).entity("uuid already exists - please choose another one").build();
				}
				this.actionData.setSystemTime(new DateTime());
			} else {
				ITimelineItem timelineItem = E2E.selectAction(this.actionData.getUuid());
				if (timelineItem != null) {
					IAction action = ActionFactory.createAction(timelineItem.getName(), timelineItem.getData(), jdbi, appConfiguration, daoProvider, viewProvider);
					if (action != null) {
						originalData = action.getActionData();
						this.actionData.setSystemTime(originalData.getSystemTime());
						this.actionData.overwriteNotReplayableData(originalData);
					}
				}
			}
			daoProvider.addActionToTimeline(this);
			if (httpMethod != HttpMethod.GET) {
				ICommand command = this.getCommand();
				if (command != null) {
					command.execute();
					if (ServerConfiguration.REPLAY.equals(appConfiguration.getServerConfiguration().getMode())) {
						command.getCommandData().overwriteNotReplayableData(originalData);
					}
					command.publishEvents();
				} else {
					throw new WebApplicationException(actionName + " returns no command");
				}
			} else {
				this.loadDataForGetRequest();
			}
			Response response = Response.ok(this.createReponse()).build();
			databaseHandle.commitTransaction();
			return response;
		} catch (WebApplicationException x) {
			LOG.error(actionName + " failed " + x.getMessage());
			try {
				databaseHandle.rollbackTransaction();
				daoProvider.addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle);
				App.reportException(x);
			} catch (Exception ex) {
				LOG.error("failed to rollback or to save or report exception " + ex.getMessage());
			}
			return Response.status(x.getResponse().getStatusInfo()).entity(x.getMessage()).build();
		} catch (Exception x) {
			LOG.error(actionName + " failed " + x.getMessage());
			try {
				databaseHandle.rollbackTransaction();
				daoProvider.addExceptionToTimeline(this.actionData.getUuid(), x, databaseHandle);
				App.reportException(x);
			} catch (Exception ex) {
				LOG.error("failed to rollback or to save or report exception " + ex.getMessage());
			}
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

	protected Object createReponse() {
		return this.actionData.getUuid();
	}

}
