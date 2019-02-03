package com.anfelisa.ace;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

public class AceDao {

	public void truncateTimelineTable(Handle handle) {
		handle.execute("TRUNCATE TABLE timeline");
	}

	public boolean contains(Handle handle, String uuid) {
		Optional<Integer> optional = handle
				.createQuery("SELECT count(uuid) " + "FROM timeline "
						+ "where uuid = :uuid")
				.bind("uuid", uuid)
				.mapTo((Integer.class)).findFirst();
		Integer count = optional.isPresent() ? optional.get() : 0;
		return count > 0;
	}

	public void insertIntoTimeline(Handle handle, String type, String method, String name, String data, String uuid) {
	Update statement = handle.createUpdate("INSERT INTO timeline (type, method, name, time, data, uuid) " + "VALUES (:type, :method, :name, NOW(), :data, :uuid);");
	statement.bind("type", type);
	if (method != null) {
		statement.bind("method", method);
	} else {
		statement.bind("method", "---");
	}
	statement.bind("name", name);
	statement.bind("data", data);
	statement.bind("uuid", uuid);
	statement.execute();
	}

	public ITimelineItem selectLastAction(Handle handle) {
		Optional<ITimelineItem> optional = handle
				.createQuery("SELECT type, method, name, time, data, uuid FROM timeline order by time desc limit 1")
				.map(new TimelineItemMapper())
				.findFirst();
		return optional.isPresent() ? optional.get() : null;
	}

	public List<ITimelineItem> selectTimeline(Handle handle) {
		return handle
				.createQuery("SELECT type, method, name, time, data, uuid FROM timeline order by time asc")
				.map(new TimelineItemMapper()).list();
	}
	
	public List<ITimelineItem> selectReplayTimeline(Handle handle) {
		return handle
				.createQuery("SELECT type, method, name, time, data, uuid FROM timeline where type = 'event' order by time asc ")
				.map(new TimelineItemMapper()).list();
	}
	

}
