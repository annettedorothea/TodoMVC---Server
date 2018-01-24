package com.anfelisa.ace.bug;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/bug")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class ResolveBugResource {
	static final Logger LOG = LoggerFactory.getLogger(ResolveBugResource.class);

	private DBI jdbi;
	
	private BugDao bugDao = new BugDao();

	public ResolveBugResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@DELETE
	@Timed
	@Path("/resolve")
	// We should protect this resource!
	public Response delete(@NotNull @QueryParam("id") int id) throws JsonProcessingException {
		Handle handle = jdbi.open();
		try {
			IBugModel bug = bugDao.selectById(handle, id);
			if (bug == null) {
				throw new WebApplicationException(Response.Status.BAD_REQUEST);
			}
			bugDao.updateResolvedById(handle, id, new DateTime());
			return Response.ok().build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} finally {
			handle.commit();
			handle.close();
		}
	}

}

/* S.D.G. */
