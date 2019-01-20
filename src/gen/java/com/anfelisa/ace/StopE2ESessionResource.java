package com.anfelisa.ace;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/e2e")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class StopE2ESessionResource {

	static final Logger LOG = LoggerFactory.getLogger(StopE2ESessionResource.class);

	public StopE2ESessionResource() {
		super();
	}

	@PUT
	@Timed
	@Path("/stop")
	public Response put() {
		E2E.reset();
		return Response.ok().build();
	}

}

