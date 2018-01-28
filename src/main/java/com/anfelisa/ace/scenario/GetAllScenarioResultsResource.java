package com.anfelisa.ace.scenario;

import java.util.List;

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

import com.anfelisa.ace.scenario.models.CustomScenarioResultDao;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/scenario-result")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetAllScenarioResultsResource {
	static final Logger LOG = LoggerFactory.getLogger(GetAllScenarioResultsResource.class);

	private DBI jdbi;

	private CustomScenarioResultDao customScenarioResultDao = new CustomScenarioResultDao();

	public GetAllScenarioResultsResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@GET
	@Timed
	@Path("/all")
	// We should protect this resource!
	public Response get(@NotNull @QueryParam("scenarioId") int scenarioId) throws JsonProcessingException {
		Handle handle = jdbi.open();
		try {
			List<IScenarioResultModel> scenarioResults = customScenarioResultDao.selectAll(handle, scenarioId);
			return Response.ok(scenarioResults).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} finally {
			handle.close();
		}
	}

}

/* S.D.G. */
