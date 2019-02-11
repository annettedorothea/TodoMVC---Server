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




package com.anfelisa.ace;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReplayEventsResource {

	private Jdbi jdbi;
	private CustomAppConfiguration configuration;

	static final Logger LOG = LoggerFactory.getLogger(ReplayEventsResource.class);

	private IDaoProvider daoProvider;
	private ViewProvider viewProvider;

	public ReplayEventsResource(Jdbi jdbi, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration configuration) {
		super();
		this.jdbi = jdbi;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
		this.configuration = configuration;
	}

	@PUT
	@Timed
	@Path("/replay-events")
	public Response put(List<ITimelineItem> timeline) throws JsonProcessingException {
		if (ServerConfiguration.LIVE.equals(configuration.getServerConfiguration().getMode())) {
			throw new WebApplicationException("replay e2e events is not available in a live environment", Response.Status.FORBIDDEN);
		}
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi);
		try {
			databaseHandle.beginTransaction();

			Handle handle = databaseHandle.getHandle();
			daoProvider.truncateAllViews(handle);
			daoProvider.getAceDao().truncateTimelineTable(handle);
			databaseHandle.commitTransaction();

			databaseHandle.beginTransaction();
			if (timeline != null) {
				for (ITimelineItem nextEvent : timeline) {
					IEvent event = EventFactory.createEvent(nextEvent.getName(), nextEvent.getData(), daoProvider, viewProvider);
					event.notifyListeners(databaseHandle.getHandle());
					daoProvider.getAceDao().addPreparingEventToTimeline(event, nextEvent.getUuid(), databaseHandle.getTimelineHandle());
					LOG.info("published " + nextEvent.getUuid() + " - " + nextEvent.getName());
				}
				LOG.info("EVENT REPLAY FINISHED: successfully replayed " + timeline.size() + " events");
			}

			databaseHandle.commitTransaction();

			return Response.ok().build();
		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			LOG.error("exception during prepare test");
			LOG.error(e.getMessage());
			throw new WebApplicationException(e);
		} finally {
			databaseHandle.close();
		}
	}

}




/******* S.D.G. *******/



