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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrepareE2EResource {

	private Jdbi jdbi;
	private CustomAppConfiguration configuration;

	static final Logger LOG = LoggerFactory.getLogger(PrepareE2EResource.class);

	private IDaoProvider daoProvider;
	private ViewProvider viewProvider;
	private E2E e2e;

	public PrepareE2EResource(Jdbi jdbi, IDaoProvider daoProvider, ViewProvider viewProvider, E2E e2e, CustomAppConfiguration configuration) {
		super();
		this.jdbi = jdbi;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
		this.e2e = e2e;
		this.configuration = configuration;
	}

	@PUT
	@Timed
	@Path("/prepare")
	public Response put(@NotNull @QueryParam("uuid") String uuid) {
		if (ServerConfiguration.LIVE.equals(configuration.getServerConfiguration().getMode())) {
			throw new WebApplicationException("prepare e2e replay is not available in a live environment", Response.Status.FORBIDDEN);
		}
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi);
		LOG.info("PREPARE ACTION " + uuid);
		try {
			databaseHandle.beginTransaction();

			int eventCount = 0;
			ITimelineItem nextAction = e2e.selectNextAction();
			while (nextAction != null && !nextAction.getUuid().equals(uuid)) {
				if (!nextAction.getMethod().equalsIgnoreCase("GET")) {
					ITimelineItem nextEvent = e2e.selectEvent(nextAction.getUuid());
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
				nextAction = e2e.selectNextAction();
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

