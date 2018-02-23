package com.anfelisa.ace;

import java.lang.reflect.Constructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/database")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class MigrateDatabaseResource {

	private DBI jdbi;

	static final Logger LOG = LoggerFactory.getLogger(MigrateDatabaseResource.class);

	private AceDao aceDao = new AceDao();

	public MigrateDatabaseResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@PUT
	@Timed
	@Path("/migrate")
	// We should protect this resource!
	public Response put(@QueryParam("uuid") String uuid) {
		//AceController.setAceExecutionMode(AceExecutionMode.MIGRATE);

		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		LOG.info("START MIGRATION");
		try {
			databaseHandle.beginTransaction();

			Handle handle = databaseHandle.getHandle();
			
			// truncate all view-tables

			ITimelineItem nextAction = aceDao.selectNextAction(handle, null);
			while (nextAction != null && !nextAction.getUuid().equals(uuid)) {
				if (!nextAction.getMethod().equalsIgnoreCase("GET")) {
					ITimelineItem nextEvent = aceDao.selectEvent(handle, nextAction.getUuid());
					LOG.info("PUBLISH EVENT " + nextEvent);
					Class<?> cl = Class.forName(nextEvent.getName());
					Constructor<?> con = cl.getConstructor(DatabaseHandle.class);
					IEvent event = (IEvent) con.newInstance(databaseHandle);
					event.initEventData(nextEvent.getData());
					event.notifyListeners();
					AceController.addPreparingEventToTimeline(event, nextAction.getUuid());
				}
				nextAction = aceDao.selectNextAction(handle, nextAction.getUuid());
			}

			databaseHandle.commitTransaction();

			LOG.info("MIGRATION FINISHED");
			return Response.ok().build();
		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			LOG.info("MIGRATION ABORTED " + e.getMessage());
			throw new WebApplicationException(e);
		} finally {
			databaseHandle.close();
			AceController.setAceExecutionMode(AceExecutionMode.LIVE);
		}
	}

}

