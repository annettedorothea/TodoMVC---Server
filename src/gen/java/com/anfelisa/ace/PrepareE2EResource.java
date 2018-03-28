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

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/e2e")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class PrepareE2EResource {

	private DBI jdbi;

	static final Logger LOG = LoggerFactory.getLogger(PrepareE2EResource.class);

	private AceDao aceDao = new AceDao();

	public PrepareE2EResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@PUT
	@Timed
	@Path("/prepare")
	public Response put(@NotNull @QueryParam("uuid") String uuid) {
		ITimelineItem actionToBePrepared = E2E.selectAction(uuid);

		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		LOG.info("PREPARE ACTION " + actionToBePrepared);
		try {
			databaseHandle.beginTransaction();

			ITimelineItem lastAction = aceDao.selectLastAction(databaseHandle.getHandle());

			int eventCount = 0;
			ITimelineItem nextAction = E2E.selectNextAction(lastAction != null ? lastAction.getUuid() : null);
			while (nextAction != null && !nextAction.getUuid().equals(uuid)) {
				if (!nextAction.getMethod().equalsIgnoreCase("GET")) {
					ITimelineItem nextEvent = E2E.selectEvent(nextAction.getUuid());
					if (nextEvent != null) {
						LOG.info("PUBLISH EVENT " + nextEvent);
						Class<?> cl = Class.forName(nextEvent.getName());
						Constructor<?> con = cl.getConstructor(DatabaseHandle.class);
						IEvent event = (IEvent) con.newInstance(databaseHandle);
						event.initEventData(nextEvent.getData());
						event.notifyListeners();
						AceController.addPreparingEventToTimeline(event, nextAction.getUuid());
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
