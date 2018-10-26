package com.anfelisa.ace;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;

import org.jdbi.v3.core.Handle;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/e2e")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class StartE2ESessionResource {

	static final Logger LOG = LoggerFactory.getLogger(StartE2ESessionResource.class);

	private Jdbi jdbi;

	private IDaoProvider daoProvider = new DaoProvider();

	public StartE2ESessionResource(Jdbi jdbi, IDaoProvider daoProvider) {
		super();
		this.jdbi = jdbi;
		this.daoProvider = daoProvider;
	}

	@PUT
	@Timed
	@Path("/start")
	public Response put(@NotNull List<ITimelineItem> timeline) throws JsonProcessingException {
		if (E2E.sessionIsRunning && E2E.sessionStartedAt.plusMinutes(1).isAfterNow()) {
			throw new WebApplicationException("session is already running", Response.Status.SERVICE_UNAVAILABLE);
		}
		E2E.sessionIsRunning = true;
		E2E.sessionStartedAt = new DateTime(System.currentTimeMillis());
		E2E.timeline = timeline;
		
		Handle handle = jdbi.open();
		try {
			handle.begin();
			
			daoProvider.getAceDao().truncateTimelineTable(handle);

			daoProvider.truncateAllViews(handle);

			handle.commit();

			return Response.ok().build();
		} catch (Exception e) {
			handle.rollback();
			throw new WebApplicationException(e);
		} finally {
			handle.close();
		}
	}

}
