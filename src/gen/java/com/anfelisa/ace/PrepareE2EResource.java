package com.anfelisa.ace;

import java.lang.reflect.Constructor;

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
		ITimelineItem actionToBePrepared = E2E.selectAction(uuid);
		if (actionToBePrepared == null) {
			return Response.ok("prepared action " + uuid + " by doing nothing - action was not found").build();
		}

		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), jdbi.open());
		LOG.info("PREPARE ACTION " + actionToBePrepared);
		try {
			databaseHandle.beginTransaction();

			ITimelineItem lastAction = daoProvider.getAceDao().selectLastAction(databaseHandle.getHandle());

			int eventCount = 0;
			ITimelineItem nextAction = E2E.selectNextAction(lastAction != null ? lastAction.getUuid() : null);
			while (nextAction != null && !nextAction.getUuid().equals(uuid)) {
				if (!nextAction.getMethod().equalsIgnoreCase("GET")) {
					ITimelineItem nextEvent = E2E.selectEvent(nextAction.getUuid());
					if (nextEvent != null) {
						LOG.info("PUBLISH EVENT " + nextEvent);
						Class<?> cl = Class.forName(nextEvent.getName());
						Constructor<?> con = cl.getConstructor(DatabaseHandle.class, IDaoProvider.class, ViewProvider.class);
						IEvent event = (IEvent) con.newInstance(databaseHandle, daoProvider, viewProvider);
						event.initEventData(nextEvent.getData());
						event.notifyListeners();
						daoProvider.addPreparingEventToTimeline(event, nextAction.getUuid());
						eventCount++;
					}
				}
				nextAction = E2E.selectNextAction(nextAction.getUuid());
			}

			databaseHandle.commitTransaction();
			return Response.ok("prepared action " + uuid + " by publishing " + eventCount + " events").build();
		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			throw new WebApplicationException(e);
		} finally {
			databaseHandle.close();
		}
	}

}

