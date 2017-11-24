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

import com.anfelisa.todo.app.bug.BugDao;
import com.anfelisa.todo.app.bug.IBugModel;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/bug")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetAllBugsResource {
	static final Logger LOG = LoggerFactory.getLogger(GetAllBugsResource.class);

	private DBI jdbi;

	private BugDao bugDao = new BugDao();

	public GetAllBugsResource(DBI jdbi) {
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
			List<IBugModel> bugs = bugDao.selectAll(handle);
			return Response.ok(bugs).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} finally {
			handle.close();
		}
	}

}

/* S.D.G. */
