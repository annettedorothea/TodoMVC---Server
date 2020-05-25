/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */




package de.acegen;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/e2e")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StartE2ESessionResource {

	static final Logger LOG = LoggerFactory.getLogger(StartE2ESessionResource.class);

	private Jdbi jdbi;			
	private CustomAppConfiguration configuration;

	private IDaoProvider daoProvider = new DaoProvider();
	
	private E2E e2e;

	public StartE2ESessionResource(Jdbi jdbi, IDaoProvider daoProvider, E2E e2e, CustomAppConfiguration configuration) {
		super();
		this.jdbi = jdbi;
		this.daoProvider = daoProvider;
		this.e2e = e2e;
		this.configuration = configuration;
	}

	@PUT
	@Path("/start")
	public Response put(@NotNull List<ITimelineItem> timeline) throws JsonProcessingException {
		if (Config.LIVE.equals(configuration.getConfig().getMode())) {
			throw new WebApplicationException("start e2e session is not available in a live environment", Response.Status.FORBIDDEN);
		}
		if (e2e.isSessionRunning() && e2e.getSessionStartedAt().plusMinutes(1).isAfterNow()) {
			throw new WebApplicationException("session is already running", Response.Status.SERVICE_UNAVAILABLE);
		}
		e2e.init(timeline);
		
		PersistenceHandle handle = new PersistenceHandle(jdbi.open());
		try {
			handle.getHandle().begin();
			
			daoProvider.getAceDao().truncateTimelineTable(handle);

			daoProvider.truncateAllViews(handle);

			handle.getHandle().commit();

			return Response.ok().build();
		} catch (Exception e) {
			handle.getHandle().rollback();
			throw new WebApplicationException(e);
		} finally {
			handle.getHandle().close();
		}
	}

}



/******* S.D.G. *******/



