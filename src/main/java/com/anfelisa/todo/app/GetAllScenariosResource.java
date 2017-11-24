package com.anfelisa.todo.app;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.app.scenario.IScenarioModel;
import com.anfelisa.todo.app.scenario.ScenarioDao;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/scenario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetAllScenariosResource {
	static final Logger LOG = LoggerFactory.getLogger(GetAllScenariosResource.class);

	private DBI jdbi;

	private ScenarioDao scenarioDao = new ScenarioDao();

	public GetAllScenariosResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@GET
	@Timed
	@Path("/all")
	// We should protect this resource!
	public Response get() throws JsonProcessingException {
		Handle handle = jdbi.open();
		try {
			List<IScenarioModel> scenarios = scenarioDao.selectAll(handle);
			return Response.ok(scenarios).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} finally {
			handle.close();
		}
	}

}

/* S.D.G. */
