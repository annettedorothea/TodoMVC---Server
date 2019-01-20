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


import com.anfelisa.todo.data.ToggleAllData;

import com.anfelisa.todo.commands.ToggleAllCommand;

@SuppressWarnings("unused")
@Path("/todos/toggle-all")
public abstract class AbstractToggleAllAction extends Action<ToggleAllData> {

	public AbstractToggleAllAction(Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.ToggleAllAction", HttpMethod.PUT, jdbi, appConfiguration, daoProvider, viewProvider);
	}

	@Override
	public ICommand getCommand() {
		return new ToggleAllCommand(this.actionData, databaseHandle, daoProvider, viewProvider);
	}
	
	public void setActionData(IDataContainer data) {
		this.actionData = (ToggleAllData)data;
	}

	protected final void loadDataForGetRequest() {
	}

	@PUT
	@Timed
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response toggleAllResource(
			@NotNull @QueryParam("uuid") String uuid) 
			throws JsonProcessingException {
		this.actionData = new ToggleAllData(uuid);
		return this.apply();
	}

}

/*       S.D.G.       */
