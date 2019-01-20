package com.anfelisa.todo.actions;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import io.dropwizard.auth.Auth;

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.IDataContainer;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jdbi.v3.core.Jdbi;

import com.anfelisa.ace.Action;
import com.anfelisa.ace.HttpMethod;
import com.anfelisa.ace.ICommand;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


import com.anfelisa.todo.data.TodoData;

import com.anfelisa.todo.commands.UpdateTodoCommand;

@SuppressWarnings("unused")
@Path("/todos/update")
public abstract class AbstractUpdateTodoAction extends Action<TodoData> {

	public AbstractUpdateTodoAction(Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.UpdateTodoAction", HttpMethod.PUT, jdbi, appConfiguration, daoProvider, viewProvider);
	}

	@Override
	public ICommand getCommand() {
		return new UpdateTodoCommand(this.actionData, databaseHandle, daoProvider, viewProvider);
	}
	
	public void setActionData(IDataContainer data) {
		this.actionData = (TodoData)data;
	}

	protected final void loadDataForGetRequest() {
	}

	@PUT
	@Timed
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTodoResource(
			@NotNull TodoData payload)
			throws JsonProcessingException {
		this.actionData = new TodoData(payload.getUuid());
		this.actionData.setId(payload.getId());
		this.actionData.setDescription(payload.getDescription());
		return this.apply();
	}

}

/*       S.D.G.       */
