package com.anfelisa.ace;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/test")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class ReplayEventsResource {

	private Jdbi jdbi;

	static final Logger LOG = LoggerFactory.getLogger(ReplayEventsResource.class);

	private IDaoProvider daoProvider;
	private ViewProvider viewProvider;

	public ReplayEventsResource(Jdbi jdbi, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super();
		this.jdbi = jdbi;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
	}

	@PUT
	@Timed
	@Path("/replay-events")
	public Response put(List<ITimelineItem> timeline) {
		DatabaseHandle databaseHandle = new DatabaseHandle(jdbi.open(), jdbi.open());
		try {
			databaseHandle.beginTransaction();

			Handle handle = databaseHandle.getHandle();
			daoProvider.truncateAllViews(handle);
			daoProvider.getAceDao().truncateTimelineTable(handle);

			if (timeline != null) {
				for (ITimelineItem nextEvent : timeline) {
					IEvent event = EventFactory.createEvent(nextEvent.getName(), nextEvent.getData(), databaseHandle,
							daoProvider, viewProvider);
					event.notifyListeners();
					daoProvider.addPreparingEventToTimeline(event, nextEvent.getUuid());
					LOG.info("published " + nextEvent.getUuid() + " - " + nextEvent.getName());
				}
				LOG.info("EVENT REPLAY FINISHED: successfully replayed " + timeline.size() + " events");
			}

			databaseHandle.commitTransaction();

			return Response.ok().build();
		} catch (Exception e) {
			databaseHandle.rollbackTransaction();
			LOG.error("exception during prepare test");
			LOG.error(e.getMessage());
			throw new WebApplicationException(e);
		} finally {
			databaseHandle.close();
		}
	}

}


