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

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.WebApplicationException;

import com.anfelisa.ace.Action;
import com.anfelisa.ace.HttpMethod;
import com.anfelisa.ace.ICommand;
import org.joda.time.DateTime;

import com.anfelisa.todo.data.TodoData;

import com.anfelisa.todo.commands.CreateTodoCommand;

@SuppressWarnings("unused")
@Path("/todos/create")
public abstract class AbstractCreateTodoAction extends Action<TodoData> {

	public AbstractCreateTodoAction(Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.CreateTodoAction", HttpMethod.POST, jdbi, appConfiguration, daoProvider, viewProvider);
	}

	@Override
	public ICommand getCommand() {
		return new CreateTodoCommand(this.actionData, databaseHandle, daoProvider, viewProvider);
	}

	protected final void loadDataForGetRequest() {
	}

	public void initActionData(String json) {
		try {
			this.actionData = mapper.readValue(json, TodoData.class);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	@POST
	@Timed
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTodoResource(
			TodoData Todo, 
			@NotNull @QueryParam("uuid") String uuid) throws JsonProcessingException {
		this.actionData = Todo;
		return this.apply();
	}
}

/*       S.D.G.       */
