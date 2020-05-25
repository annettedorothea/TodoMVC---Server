/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */




package de.acegen;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jdbi.v3.core.statement.Update;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.ws.rs.WebApplicationException;

public class AceDao {

	private JodaObjectMapper mapper;

	private ConcurrentLinkedQueue<String> lastUuids;

	public AceDao() {
		mapper = new JodaObjectMapper();
		lastUuids = new ConcurrentLinkedQueue<>();
	}

	public boolean checkUuid(String uuid) {
		boolean alreadyUsed = lastUuids.contains(uuid);
		if (alreadyUsed) {
			return false;
		}
		lastUuids.add(uuid);
		if (lastUuids.size() > 1000) {
			lastUuids.remove();
		}
		return true;
	}

	public void truncateTimelineTable(PersistenceHandle handle) {
		handle.getHandle().execute("TRUNCATE TABLE timeline");
	}

	public boolean contains(PersistenceHandle handle, String uuid) {
		Optional<Integer> optional = handle.getHandle()
				.createQuery("SELECT count(uuid) " + "FROM timeline "
						+ "where uuid = :uuid")
				.bind("uuid", uuid)
				.mapTo((Integer.class)).findFirst();
		Integer count = optional.isPresent() ? optional.get() : 0;
		return count > 0;
	}

	public void insertIntoTimeline(PersistenceHandle handle, String type, String name, String data, String uuid) {
		Update statement = handle.getHandle().createUpdate("INSERT INTO timeline (type, name, time, data, uuid) " + "VALUES (:type, :name, NOW(), :data, :uuid);");
		statement.bind("type", type);
		statement.bind("name", name);
		statement.bind("data", data);
		statement.bind("uuid", uuid);
		statement.execute();
	}

	public ITimelineItem selectLastAction(PersistenceHandle handle) {
		Optional<ITimelineItem> optional = handle.getHandle()
				.createQuery("SELECT type, name, time, data, uuid FROM timeline order by time desc limit 1")
				.map(new TimelineItemMapper())
				.findFirst();
		return optional.isPresent() ? optional.get() : null;
	}

	public List<ITimelineItem> selectTimeline(PersistenceHandle handle) {
		return handle.getHandle()
				.createQuery("SELECT type, name, time, data, uuid FROM timeline order by time asc")
				.map(new TimelineItemMapper()).list();
	}
	
	public List<ITimelineItem> selectReplayTimeline(PersistenceHandle handle) {
		return handle.getHandle()
				.createQuery("SELECT type, name, time, data, uuid FROM timeline where type = 'event' order by time asc ")
				.map(new TimelineItemMapper()).list();
	}
	
	public void addActionToTimeline(IAction action, PersistenceHandle timelineHandle) {
		try {
			String json = mapper.writeValueAsString(action.getActionData());
			addItemToTimeline("action", action.getActionName(), json,
					action.getActionData().getUuid(), timelineHandle);
		} catch (JsonProcessingException e) {
			throw new WebApplicationException(e);
		}
	}

	public void addCommandToTimeline(ICommand command, PersistenceHandle timelineHandle) {
		try {
			addItemToTimeline("command", command.getCommandName(),
					mapper.writeValueAsString(command.getCommandData()), command.getCommandData().getUuid(),
					timelineHandle);
		} catch (JsonProcessingException e) {
			throw new WebApplicationException(e);
		}
	}

	public void addEventToTimeline(IEvent event, PersistenceHandle timelineHandle) {
		try {
			addItemToTimeline("event", event.getEventName(), mapper.writeValueAsString(event.getEventData()),
					event.getEventData().getUuid(), timelineHandle);
		} catch (JsonProcessingException e) {
			throw new WebApplicationException(e);
		}
	}

	public void addPreparingEventToTimeline(IEvent event, String uuid, PersistenceHandle timelineHandle) {
		try {
			addItemToTimeline("preparing event", event.getEventName(),
					mapper.writeValueAsString(event.getEventData()), uuid, timelineHandle);
		} catch (JsonProcessingException e) {
			throw new WebApplicationException(e);
		}
	}

	public void addExceptionToTimeline(String uuid, Throwable x, PersistenceHandle timelineHandle) {
		this.insertIntoTimeline(timelineHandle, "exception", x.getClass().getName(),
				x.getMessage() != null ? x.getMessage() : "", uuid);
	}

	private void addItemToTimeline(String type, String name, String json, String uuid,
			PersistenceHandle timelineHandle) {
		this.insertIntoTimeline(timelineHandle, type, name, json, uuid);
	}

}



/******* S.D.G. *******/



