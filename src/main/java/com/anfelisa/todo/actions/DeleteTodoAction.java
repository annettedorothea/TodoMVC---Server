package com.anfelisa.todo.actions;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoIdData;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/todos")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class DeleteTodoAction extends AbstractDeleteTodoAction {

	static final Logger LOG = LoggerFactory.getLogger(DeleteTodoAction.class);

	public DeleteTodoAction(DBI jdbi, DBI jdbiTimeline) {
		super(jdbi, jdbiTimeline);
	}

	@DELETE
	@Timed
	@Path("/delete")
	@PermitAll
	public Response delete(@NotNull @QueryParam("uuid") String uuid, @NotNull @QueryParam("id") int id)
			throws JsonProcessingException {
		this.actionData = new TodoIdData(uuid).withId(id);
		return this.apply();
	}

}

/* S.D.G. */
