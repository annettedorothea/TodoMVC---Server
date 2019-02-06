package com.anfelisa.ace;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;

import org.joda.time.DateTime;

import com.codahale.metrics.annotation.Timed;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SetSystemTimeResource {

	public static DateTime systemTime;
	private CustomAppConfiguration configuration;
	
	public SetSystemTimeResource(CustomAppConfiguration configuration) {
		super();
		this.configuration = configuration;
	}
	
	@PUT
	@Timed
	@Path("/system-time")
	public Response put(@NotNull String systemTime) {
		if (ServerConfiguration.LIVE.equals(configuration.getServerConfiguration().getMode())) {
			throw new WebApplicationException("set system time is not available in a live environment", Response.Status.FORBIDDEN);
		}
		SetSystemTimeResource.systemTime = new DateTime(systemTime);
		return Response.ok("set system time to " + systemTime).build();
	}

}

