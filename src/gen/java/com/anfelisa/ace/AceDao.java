package com.anfelisa.ace;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Update;

public class AceDao {

	private static String schemaName = "public";

	public static void setSchemaName(String schemaName) {
		AceDao.schemaName = schemaName;
	}

	private String timelineTable() {
		if (StringUtils.isBlank(AceDao.schemaName)) {
			return "public.timeline";
		} else {
			return AceDao.schemaName + ".timeline";
		}
	}

	private String errorTimelineTable() {
		if (StringUtils.isBlank(AceDao.schemaName)) {
			return "public.errorTimeline";
		} else {
			return AceDao.schemaName + ".errorTimeline";
		}
	}

	public void truncateTimelineTable(Handle handle) {
		handle.execute("TRUNCATE " + timelineTable());
	}

	public void truncateErrorTimelineTable(Handle handle) {
		handle.execute("TRUNCATE " + errorTimelineTable());
	}

	public void insertIntoTimeline(Handle handle, String type, String method, String name, String data, String uuid) {
		Update statement = handle.createStatement("INSERT INTO " + timelineTable()
				+ " (type, method, name, time, data, uuid) " + "VALUES (:type, :method, :name, NOW(), :data, :uuid);");
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

	public void insertIntoErrorTimeline(Handle handle, String type, String method, String name, String data,
			String uuid) {
		if (handle != null) {
			Update statement = handle.createStatement("INSERT INTO " + errorTimelineTable()
					+ " (type, method, name, time, data, uuid) " + "VALUES (:type, :method, :name, NOW(), :data, :uuid);");
			statement.bind("type", type);
			if (method != null) {
				statement.bind("method", method);
			} else {
				statement.bind("method", "---");
			}
			statement.bind("name", name);
			if (data != null) {
				statement.bind("data", data);
			} else {
				statement.bind("data", "unknown exception");
			}
			statement.bind("uuid", uuid);
			statement.execute();
		}
	}

	public ITimelineItem selectLastAction(Handle handle) {
		return handle
				.createQuery("SELECT type, method, name, time, data, uuid " + "FROM " + timelineTable() + " "
						+ "where type = 'action' " + "order by time desc " + "limit 1")
				.map(new TimelineItemMapper()).first();
	}

	public List<ITimelineItem> selectTimeline(Handle handle) {
		return handle
				.createQuery("SELECT type, method, name, time, data, uuid " + "FROM " + timelineTable() + " "
						+ "order by time asc ")
				.map(new TimelineItemMapper()).list();
	}

}
