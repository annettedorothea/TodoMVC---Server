package com.anfelisa.ace;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;

@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetServerInfoResource {

	public GetServerInfoResource() {
		super();
	}

	@GET
	@Timed
	@Path("/info")
	public Response put() {
		return Response.ok(new ServerInfo(App.getVersion())).build();
	}

}

