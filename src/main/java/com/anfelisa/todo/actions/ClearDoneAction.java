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

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.ClearDoneData;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/todos")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class ClearDoneAction extends AbstractClearDoneAction {

	static final Logger LOG = LoggerFactory.getLogger(ClearDoneAction.class);

	public ClearDoneAction(DBI jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(jdbi, appConfiguration, daoProvider, viewProvider);
	}

	@DELETE
	@Timed
	@Path("/clear-done")
	@PermitAll
	public Response delete(@NotNull @QueryParam("uuid") String uuid) throws JsonProcessingException {
		this.actionData = new ClearDoneData(uuid);
		return this.apply();
	}

}

/* S.D.G. */
