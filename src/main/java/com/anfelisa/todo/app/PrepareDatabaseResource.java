package com.anfelisa.todo.app;

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
import com.anfelisa.ace.IAction;
import com.anfelisa.ace.ICommand;
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

			ITimelineItem lastItem = aceDao.selectLastAction(databaseHandle.getHandle());

			ITimelineItem nextItem = aceDao.selectNextAction(timelineHandle,
					lastItem != null ? lastItem.getUuid() : null);
			while (nextItem != null && !nextItem.getUuid().equals(uuid)) {
				if (!nextItem.getMethod().equalsIgnoreCase("GET")) {
					LOG.info("APPLY ACTION " + nextItem);
					Class<?> cl = Class.forName(nextItem.getName());
					Constructor<?> con = cl.getConstructor(DBI.class, DBI.class);
					IAction action = (IAction) con.newInstance(jdbi, jdbiTimeline);
					action.initActionData(nextItem.getData());
					action.setDatabaseHandle(databaseHandle);
					AceController.addActionToTimeline(action);

					ICommand command = action.getCommand();
					if (command != null) {
						command.execute();
					}
				}
				nextItem = aceDao.selectNextAction(timelineHandle, nextItem.getUuid());
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
