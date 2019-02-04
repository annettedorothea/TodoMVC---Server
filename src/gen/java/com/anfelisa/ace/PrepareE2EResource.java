package com.anfelisa.ace;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/e2e")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class PrepareE2EResource {

	private Jdbi jdbi;

	static final Logger LOG = LoggerFactory.getLogger(PrepareE2EResource.class);

	private IDaoProvider daoProvider;
	private ViewProvider viewProvider;

	public PrepareE2EResource(Jdbi jdbi, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super();
		this.jdbi = jdbi;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
	}

	@PUT
	@Timed
	@Path("/prepare")
	public Response put(@NotNull @QueryParam("uuid") String uuid) {
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi);
		LOG.info("PREPARE ACTION " + uuid);
		try {
			databaseHandle.beginTransaction();

			int eventCount = 0;
			ITimelineItem nextAction = E2E.selectNextAction();
			while (nextAction != null && !nextAction.getUuid().equals(uuid)) {
				if (!nextAction.getMethod().equalsIgnoreCase("GET")) {
					ITimelineItem nextEvent = E2E.selectEvent(nextAction.getUuid());
					if (nextEvent != null) {
						LOG.info("PUBLISH EVENT " + nextEvent.getUuid() + " - " + nextEvent.getName());
						IEvent event = EventFactory.createEvent(nextEvent.getName(), nextEvent.getData(), daoProvider, viewProvider);
						if (event != null) {
							event.notifyListeners(databaseHandle.getHandle());
							daoProvider.getAceDao().addPreparingEventToTimeline(event, nextAction.getUuid(), databaseHandle.getTimelineHandle());
							eventCount++;
						} else {
							LOG.error("failed to create " + nextEvent.getName());
						}
					}
				}
				nextAction = E2E.selectNextAction();
			}

			databaseHandle.commitTransaction();
			return Response.ok("prepared action " + uuid + " by publishing " + eventCount + " events").build();
		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			LOG.error("exception during prepare action " + uuid);
			LOG.error(e.getMessage());
			throw new WebApplicationException(e);
		} finally {
			databaseHandle.close();
		}
	}

}

