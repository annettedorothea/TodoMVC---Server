package com.anfelisa.ace;

import java.lang.reflect.Constructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoHistoryDao;
import com.codahale.metrics.annotation.Timed;

@Path("/database")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class MigrateDatabaseResource {

	private DBI jdbi;

	static final Logger LOG = LoggerFactory.getLogger(MigrateDatabaseResource.class);
	
	private AceDao aceDao = new AceDao();

	private TodoDao todoDao = new TodoDao();
	private TodoHistoryDao todoHistoryDao = new TodoHistoryDao();

	public MigrateDatabaseResource(DBI jdbi) {
		super();
		this.jdbi = jdbi;
	}

	@PUT
	@Timed
	@Path("/migrate")
	// We should protect this resource!
	public Response put() {
		AceController.setAceExecutionMode(AceExecutionMode.MIGRATE);
		
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), null);
		LOG.info("START MIGRATION");
		try {
			databaseHandle.beginTransaction();
			todoHistoryDao.truncate(databaseHandle.getHandle());
			todoDao.truncate(databaseHandle.getHandle());

			ITimelineItem nextItem = aceDao.selectNextCommand(databaseHandle.getHandle(), null);
			while (nextItem != null) {
				LOG.info("PUBLISH COMMAND " + nextItem);
				Class<?> cl = Class.forName(nextItem.getName());
				Constructor<?> con = cl.getConstructor(DatabaseHandle.class);
				ICommand command = (ICommand) con.newInstance(databaseHandle);
				command.initCommandData(nextItem.getData());
				command.publishEvents();
				nextItem = aceDao.selectNextCommand(databaseHandle.getHandle(), nextItem.getUuid());
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
