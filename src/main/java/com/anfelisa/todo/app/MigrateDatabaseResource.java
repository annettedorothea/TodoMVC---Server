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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.AceController;
import com.anfelisa.ace.AceDao;
import com.anfelisa.ace.AceExecutionMode;
import com.anfelisa.ace.DatabaseHandle;
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
	public Response put(@NotNull @QueryParam("uuid") String uuid) {
		AceController.setAceExecutionMode(AceExecutionMode.MIGRATE);
		
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		LOG.info("START MIGRATION");
		try {
			databaseHandle.beginTransaction();

			todoDao.truncate(databaseHandle.getHandle());

			ITimelineItem nextItem = aceDao.selectNextEvent(databaseHandle.getHandle(), null);
			while (nextItem != null) {
				LOG.info("PUBLISH EVENT " + nextItem);
				Class<?> cl = Class.forName(nextItem.getName());
				Constructor<?> con = cl.getConstructor(DatabaseHandle.class);
				IEvent event = (IEvent) con.newInstance(databaseHandle);
				event.initEventData(nextItem.getData());
				event.notifyListeners();
				nextItem = aceDao.selectNextEvent(databaseHandle.getHandle(), nextItem.getUuid());
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
