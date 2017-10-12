package com.anfelisa.todo.actions;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoToggleData;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/todos")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class ToggleTodoAction extends AbstractToggleTodoAction {

	static final Logger LOG = LoggerFactory.getLogger(ToggleTodoAction.class);

	public ToggleTodoAction(DBI jdbi, DBI jdbiTimeline) {
		super(jdbi, jdbiTimeline);
	}

	@PUT
	@Timed
	@Path("/toggle")
	@PermitAll
	public Response put(@NotNull @QueryParam("uuid") String uuid, @NotNull @QueryParam("id") int id)
			throws JsonProcessingException {
		this.actionData = new TodoToggleData(uuid).withId(id);
		return this.apply();
	}

}

/* S.D.G. */
