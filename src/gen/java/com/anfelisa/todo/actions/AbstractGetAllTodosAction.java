/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */




package com.anfelisa.todo.actions;

import javax.validation.constraints.NotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.jdbi.v3.core.Jdbi;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acegen.Action;
import de.acegen.App;
import de.acegen.CustomAppConfiguration;
import de.acegen.DatabaseHandle;
import de.acegen.E2E;
import de.acegen.HttpMethod;
import de.acegen.ICommand;
import de.acegen.IDaoProvider;
import de.acegen.IDataContainer;
import de.acegen.ITimelineItem;
import de.acegen.JodaObjectMapper;
import de.acegen.ServerConfiguration;
import de.acegen.ViewProvider;
import de.acegen.NotReplayableDataProvider;
import de.acegen.PersistenceHandle;
import de.acegen.PersistenceConnection;


import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.jdbi.v3.core.Handle;


import javax.ws.rs.GET;

import com.anfelisa.todo.data.ITodoListData;
import com.anfelisa.todo.data.TodoListData;

@Path("/todos/all")
@SuppressWarnings("unused")
public abstract class AbstractGetAllTodosAction extends Action<ITodoListData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractGetAllTodosAction.class);
	
	private DatabaseHandle databaseHandle;
	private PersistenceConnection persistenceConnection;
	protected JodaObjectMapper mapper;
	protected CustomAppConfiguration appConfiguration;
	protected IDaoProvider daoProvider;
	private ViewProvider viewProvider;
	private E2E e2e;
	
	
	public AbstractGetAllTodosAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider, E2E e2e) {
		super("com.anfelisa.todo.actions.GetAllTodosAction", HttpMethod.GET);
		this.persistenceConnection = persistenceConnection;
		mapper = new JodaObjectMapper();
		this.appConfiguration = appConfiguration;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
		this.e2e = e2e;
	}

	@Override
	public ICommand getCommand() {
		return null;
	}
	
	public void setActionData(IDataContainer data) {
		this.actionData = (ITodoListData)data;
	}

	protected abstract void loadDataForGetRequest(PersistenceHandle readonlyHandle);

	@GET
	@Timed
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllTodosResource(
			@NotNull @QueryParam("uuid") String uuid) 
			throws JsonProcessingException {
		this.actionData = new TodoListData(uuid);
		return this.apply();
	}

	public Response apply() {
		databaseHandle = new DatabaseHandle(persistenceConnection.getJdbi(), appConfiguration);
		databaseHandle.beginTransaction();
		try {
			if (ServerConfiguration.DEV.equals(appConfiguration.getServerConfiguration().getMode())
					|| ServerConfiguration.LIVE.equals(appConfiguration.getServerConfiguration().getMode())) {
				if (appConfiguration.getServerConfiguration().writeTimeline()) {
					if (daoProvider.getAceDao().contains(databaseHandle.getHandle(), this.actionData.getUuid())) {
						databaseHandle.commitTransaction();
						throwBadRequest("uuid already exists - please choose another one");
					}
				}
				
				this.actionData.setSystemTime(DateTime.now().withZone(DateTimeZone.UTC));
				this.initActionData();
			} else if (ServerConfiguration.REPLAY.equals(appConfiguration.getServerConfiguration().getMode())) {
				ITimelineItem timelineItem = e2e.selectAction(this.actionData.getUuid());
				IDataContainer originalData = AceDataFactory.createAceData(timelineItem.getName(), timelineItem.getData());
				ITodoListData originalActionData = (ITodoListData)originalData;
				this.actionData.setSystemTime(originalActionData.getSystemTime());
			} else if (ServerConfiguration.TEST.equals(appConfiguration.getServerConfiguration().getMode())) {
				if (NotReplayableDataProvider.getSystemTime() != null) {
					this.actionData.setSystemTime(NotReplayableDataProvider.getSystemTime());
				}
			}
			this.loadDataForGetRequest(this.databaseHandle.getReadonlyHandle());
			
			if (appConfiguration.getServerConfiguration().writeTimeline()) {
				daoProvider.getAceDao().addActionToTimeline(this, this.databaseHandle.getTimelineHandle());
			}
			Response response = Response.ok(this.createReponse()).build();
			databaseHandle.commitTransaction();
			return response;
		} catch (WebApplicationException x) {
			LOG.error(actionName + " returns {} due to {} ", x.getResponse().getStatusInfo(), x.getMessage());
			try {
				databaseHandle.rollbackTransaction();
				if (appConfiguration.getServerConfiguration().writeError()) {
					daoProvider.getAceDao().addExceptionToTimeline(this.actionData.getUuid(), x, this.databaseHandle.getTimelineHandle());
				}
				App.reportException(x);
			} catch (Exception ex) {
				LOG.error("failed to rollback or to save or report exception " + ex.getMessage());
			}
			return Response.status(x.getResponse().getStatusInfo()).entity(x.getMessage()).build();
		} catch (Exception x) {
			LOG.error(actionName + " failed " + x.getMessage());
			x.printStackTrace();
			try {
				databaseHandle.rollbackTransaction();
				if (appConfiguration.getServerConfiguration().writeError()) {
					daoProvider.getAceDao().addExceptionToTimeline(this.actionData.getUuid(), x, this.databaseHandle.getTimelineHandle());
				}
				App.reportException(x);
			} catch (Exception ex) {
				LOG.error("failed to rollback or to save or report exception " + ex.getMessage());
			}
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
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
		} finally {
			databaseHandle.close();
			if (ServerConfiguration.TEST.equals(appConfiguration.getServerConfiguration().getMode())) {
				NotReplayableDataProvider.clear();
			}
		}
	}


	protected Object createReponse() {
		return new com.anfelisa.todo.data.GetAllTodosResponse(this.actionData);
	}
	
}




/******* S.D.G. *******/



