package com.anfelisa.todo.actions;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoData;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/todos")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class UpdateTodoAction extends AbstractUpdateTodoAction {

	static final Logger LOG = LoggerFactory.getLogger(UpdateTodoAction.class);

	public UpdateTodoAction(DBI jdbi) {
		super(jdbi);
	}

	@PUT
	@Timed
	@Path("/update")
	@PermitAll
	public Response put(@NotNull TodoData data) throws JsonProcessingException {
		this.actionData = data;
		return this.apply();
	}

}

/* S.D.G. */
