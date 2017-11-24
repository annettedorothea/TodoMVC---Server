package com.anfelisa.todo.app;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.app.scenario.ScenarioDao;
import com.anfelisa.todo.app.scenario.ScenarioModel;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/scenario")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateScenarioResource {
	static final Logger LOG = LoggerFactory.getLogger(CreateScenarioResource.class);

	private DBI jdbi;

	private ScenarioDao scenarioDao = new ScenarioDao();

	public CreateScenarioResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@POST
	@Timed
	@Path("/create")
	// We should protect this resource!
	public Response post(@NotNull ScenarioModel data) throws JsonProcessingException {
		Handle handle = jdbi.open();
		try {
			data.setCreatedDateTime(new DateTime());
			Integer id = scenarioDao.insert(handle, data);
			return Response.ok(id).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} finally {
			handle.close();
		}
	}

}

/* S.D.G. */
