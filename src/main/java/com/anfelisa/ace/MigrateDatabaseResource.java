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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.AceController;
import com.anfelisa.ace.AceDao;
import com.anfelisa.ace.AceExecutionMode;
import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IAction;
import com.anfelisa.ace.ICommand;
import com.anfelisa.ace.IEvent;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.todo.models.TodoDao;
import com.codahale.metrics.annotation.Timed;

@Path("/database")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class MigrateDatabaseResource {

	private DBI jdbi;

	static final Logger LOG = LoggerFactory.getLogger(MigrateDatabaseResource.class);
	
	private AceDao aceDao = new AceDao();

	private TodoDao todoDao = new TodoDao();

	public MigrateDatabaseResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@PUT
	@Timed
	@Path("/migrate")
	// We should protect this resource!
	public Response put(@QueryParam("uuid") String uuid) {
		AceController.setAceExecutionMode(AceExecutionMode.MIGRATE);
		
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		LOG.info("START MIGRATION");
		try {
			databaseHandle.beginTransaction();

			todoDao.truncate(databaseHandle.getHandle());

			ITimelineItem nextItem = aceDao.selectNextEvent(databaseHandle.getHandle(), null);
			while (nextItem != null && !nextItem.getUuid().equals(uuid)) {
				LOG.info("PUBLISH EVENT " + nextItem);
				Class<?> cl = Class.forName(nextItem.getName());
				Constructor<?> con = cl.getConstructor(DatabaseHandle.class);
				IEvent event = (IEvent) con.newInstance(databaseHandle);
				event.initEventData(nextItem.getData());
				event.notifyListeners();
				nextItem = aceDao.selectNextEvent(databaseHandle.getHandle(), nextItem.getUuid());
			}
			if (uuid != null) {
				ITimelineItem lastItem = nextItem;

				nextItem = aceDao.selectNextAction(databaseHandle.getHandle(),
						lastItem != null ? lastItem.getUuid() : null);
				while (nextItem != null && !nextItem.getUuid().equals(uuid)) {
					if (!nextItem.getMethod().equalsIgnoreCase("GET")) {
						LOG.info("APPLY ACTION " + nextItem);
						Class<?> cl = Class.forName(nextItem.getName());
						Constructor<?> con = cl.getConstructor(DBI.class, DBI.class);
						IAction action = (IAction) con.newInstance(jdbi, null);
						action.initActionData(nextItem.getData());
						action.setDatabaseHandle(databaseHandle);

						ICommand command = action.getCommand();
						if (command != null) {
							command.execute();
						}
					}
					nextItem = aceDao.selectNextAction(databaseHandle.getHandle(), nextItem.getUuid());
				}
			}
			databaseHandle.commitTransaction();
			LOG.info("MIGRATION FINISHED");
			return Response.ok().build();
		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			LOG.info("MIGRATION ABORTED " +  e.getMessage());
			throw new WebApplicationException(e);
		} finally {
			databaseHandle.close();
			AceController.setAceExecutionMode(AceExecutionMode.LIVE);
		}
	}

}
