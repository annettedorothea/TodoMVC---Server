package com.anfelisa.ace;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.anfelisa.ace.AceDao;
import com.anfelisa.todo.models.TodoDao;
import com.codahale.metrics.annotation.Timed;

@Path("/database")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class ClearDatabaseResource {

	private DBI jdbi;

	private AceDao aceDao = new AceDao();

	private TodoDao todoDao = new TodoDao();

	public ClearDatabaseResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@DELETE
	@Timed
	@Path("/reset")
	public Response put() {
		Handle handle = jdbi.open();
		try {
			aceDao.truncateErrorTimelineTable(handle);
			aceDao.truncateTimelineTable(handle);
			todoDao.truncate(handle);
			return Response.ok().build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} finally {
			handle.close();
		}
	}

}
