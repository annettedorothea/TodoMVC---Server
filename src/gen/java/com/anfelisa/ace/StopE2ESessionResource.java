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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StopE2ESessionResource {

	static final Logger LOG = LoggerFactory.getLogger(StopE2ESessionResource.class);

	private E2E e2e;
	
	public StopE2ESessionResource(E2E e2e) {
		super();
		this.e2e = e2e;
	}

	@PUT
	@Timed
	@Path("/stop")
	public Response put() {
		e2e.reset();
		return Response.ok().build();
	}

}

