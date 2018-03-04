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

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

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

	private DBI jdbi;

	private AceDao aceDao = new AceDao();

	public StartE2ESessionResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@PUT
	@Timed
	@Path("/start")
	public Response put(@NotNull List<ITimelineItem> timeline) throws JsonProcessingException {
		if (E2E.sessionIsRunning) {
			throw new WebApplicationException("session is already running", Response.Status.SERVICE_UNAVAILABLE);
		}
		E2E.sessionIsRunning = true;
		E2E.sessionStartedAt = new DateTime(System.currentTimeMillis());
		E2E.timeline = timeline;
		
		Handle handle = jdbi.open();
		try {
			handle.getConnection().setAutoCommit(false);
			
			aceDao.truncateErrorTimelineTable(handle);
			aceDao.truncateTimelineTable(handle);

			AppUtils.truncateAllViews(handle);

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
