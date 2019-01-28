package com.anfelisa.ace;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;

import com.codahale.metrics.annotation.Timed;

@Path("/test")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class SetSystemTimeResource {

	public static DateTime systemTime;
	
	@PUT
	@Timed
	@Path("/system-time")
	public Response put(@NotNull String systemTime) {
		SetSystemTimeResource.systemTime = new DateTime(systemTime);
		return Response.ok("set system time to " + systemTime).build();
	}

}

