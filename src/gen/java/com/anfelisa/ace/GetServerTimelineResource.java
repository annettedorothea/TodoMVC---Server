package com.anfelisa.ace;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;

import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/e2e")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetServerTimelineResource {

	private Jdbi jdbi;

	static final Logger LOG = LoggerFactory.getLogger(GetServerTimelineResource.class);

	private AceDao aceDao = new AceDao();

	public GetServerTimelineResource(Jdbi jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@GET
	@Timed
	@Path("/timeline")
	public Response get() {
		Handle timelineHandle = jdbi.open();
		try {
			List<ITimelineItem> serverTimeline = aceDao.selectTimeline(timelineHandle);
			return Response.ok(serverTimeline).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} finally {
			timelineHandle.close();
		}
	}

}

