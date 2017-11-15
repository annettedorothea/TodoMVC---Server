package com.anfelisa.todo.app;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.actions.CreateTodoAction;
import com.anfelisa.todo.app.scenario.IScenarioModel;
import com.anfelisa.todo.app.scenario.ScenarioDao;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/scenario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetScenarioResource {
	static final Logger LOG = LoggerFactory.getLogger(CreateTodoAction.class);

	private DBI jdbi;
	
	private ScenarioDao scenarioDao = new ScenarioDao();

	public GetScenarioResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@GET
	@Timed
	@Path("/get")
	// We should protect this resource!
	public Response get(@NotNull @QueryParam("id") int id) throws JsonProcessingException {
		Handle handle = jdbi.open();
		IScenarioModel scenario = scenarioDao.selectById(handle, id);
		if (scenario == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		handle.close();
		return Response.ok(scenario).build();
	}

}

/* S.D.G. */
