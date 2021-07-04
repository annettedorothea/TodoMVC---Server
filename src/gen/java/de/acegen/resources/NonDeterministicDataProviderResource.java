/********************************************************************************
 * generated by de.acegen 1.3.0
 ********************************************************************************/




package de.acegen.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acegen.NonDeterministicDataProvider;

@Path("/test/non-deterministic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NonDeterministicDataProviderResource {

	static final Logger LOG = LoggerFactory.getLogger(NonDeterministicDataProviderResource.class);

	public NonDeterministicDataProviderResource() {
		super();
	}

	@PUT
	@Path("/value")
	public Response putValue(@QueryParam("uuid") String uuid, @QueryParam("key") String key,  @QueryParam("value") String value) {
		NonDeterministicDataProvider.put(uuid, key, value);
		return Response.ok().build();
	}

	@PUT
	@Path("/system-time")
	public Response putSystemTime(@QueryParam("uuid") String uuid, @QueryParam("system-time") String systemTime) {
		NonDeterministicDataProvider.putSystemTime(uuid, LocalDateTime.parse(systemTime, DateTimeFormatter.ISO_DATE_TIME));
		return Response.ok().build();
	}
	
}




/******* S.D.G. *******/



