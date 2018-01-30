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
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.AceController;
import com.anfelisa.ace.AceDao;
import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IEvent;
import com.anfelisa.ace.ITimelineItem;
import com.codahale.metrics.annotation.Timed;

@Path("/database")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class PrepareDatabaseResource {

	private DBI jdbi;
	private DBI jdbiTimeline;

	static final Logger LOG = LoggerFactory.getLogger(PrepareDatabaseResource.class);
	
	private AceDao aceDao = new AceDao();

	public PrepareDatabaseResource(DBI jdbi, DBI jdbiTimeline) {
		super();
		this.jdbi = jdbi;
		this.jdbiTimeline = jdbiTimeline;
	}

	@PUT
	@Timed
	@Path("/prepare")
	public Response put(@NotNull @QueryParam("uuid") String uuid) {
		Handle timelineHandle = jdbiTimeline.open();
		ITimelineItem actionToBePrepared = aceDao.selectTimelineItem(timelineHandle, uuid);
		
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		LOG.info("PREPARE ACTION " + actionToBePrepared);
		try {
			databaseHandle.beginTransaction();

			ITimelineItem lastAction = aceDao.selectLastAction(databaseHandle.getHandle());

			ITimelineItem nextAction = aceDao.selectNextAction(timelineHandle,
					lastAction != null ? lastAction.getUuid() : null);
			while (nextAction != null && !nextAction.getUuid().equals(uuid)) {
				if (!nextAction.getMethod().equalsIgnoreCase("GET")) {
					ITimelineItem nextEvent = aceDao.selectEvent(timelineHandle, nextAction.getUuid());
					LOG.info("PUBLISH EVENT " + nextEvent);
					Class<?> cl = Class.forName(nextEvent.getName());
					Constructor<?> con = cl.getConstructor(DatabaseHandle.class);
					IEvent event = (IEvent) con.newInstance(databaseHandle);
					event.initEventData(nextEvent.getData());
					event.notifyListeners();
					AceController.addPreparingEventToTimeline(event, nextAction.getUuid());
				}
				nextAction = aceDao.selectNextAction(timelineHandle, nextAction.getUuid());
			}

			databaseHandle.commitTransaction();
			return Response.ok().build();
		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			throw new WebApplicationException(e);
		} finally {
			timelineHandle.close();
			databaseHandle.close();
		}
	}

}

