package com.anfelisa.ace.bug;

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

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/bug")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetBugResource {
	static final Logger LOG = LoggerFactory.getLogger(GetBugResource.class);

	private DBI jdbi;

	private BugDao bugDao = new BugDao();

	public GetBugResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@GET
	@Timed
	@Path("/single")
	// We should protect this resource!
	public Response get(@NotNull @QueryParam("id") int id) throws JsonProcessingException {
		Handle handle = jdbi.open();
		try {
			IBugModel bug = bugDao.selectById(handle, id);
			return Response.ok(bug).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} finally {
			handle.close();
		}
	}

}

/* S.D.G. */
