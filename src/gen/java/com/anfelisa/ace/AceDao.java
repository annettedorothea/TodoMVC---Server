package com.anfelisa.ace;

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

	public void createTimelineTable(Handle handle) {
		handle.execute("CREATE TABLE if not exists " + timelineTable() + " ( id serial NOT NULL, "
				+ "type character varying NOT NULL, method character varying, name character varying, "
				+ "time timestamp with time zone NOT NULL, data character varying NOT NULL, "
				+ "uuid character varying NOT NULL, CONSTRAINT \"TimelinePkey\" PRIMARY KEY (id))");
	}

	public void createErrorTimelineTable(Handle handle) {
		handle.execute("CREATE TABLE if not exists " + errorTimelineTable() + " ( id serial NOT NULL, "
				+ "type character varying NOT NULL, method character varying, name character varying, "
				+ "time timestamp with time zone NOT NULL, data character varying NOT NULL, "
				+ "uuid character varying NOT NULL, CONSTRAINT \"ErrorTimelinePkey\" PRIMARY KEY (id))");
	}

	public void truncateTimelineTable(Handle handle) {
		handle.execute("TRUNCATE " + timelineTable());
	}

	public void truncateErrorTimelineTable(Handle handle) {
		handle.execute("TRUNCATE " + errorTimelineTable());
	}

	public void insertIntoTimeline(Handle handle, String type, String method, String name, String data, String uuid) {
		Update statement = handle.createStatement("INSERT INTO " + timelineTable()
				+ " (id, type, method, name, time, data, uuid) " + "VALUES ((select COALESCE(MAX(id),0) + 1 from " + timelineTable()
				+ "), :type, :method, :name, NOW(), :data, :uuid);");
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
					+ " (id, type, method, name, time, data, uuid) " + "VALUES ((select COALESCE(MAX(id),0) + 1 from " + errorTimelineTable()
					+ "), :type, :method, :name, NOW(), :data, :uuid);");
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

	public ITimelineItem selectNextAction(Handle handle, String uuid) {
		if (uuid != null) {
			return handle
					.createQuery("SELECT id, type, method, name, time, data, uuid " + "FROM " + timelineTable() + " "
							+ "where id > " + "(select id from " + timelineTable()
							+ " where uuid = :uuid and type = 'action' limit 1) " + "and type = 'action' "
							+ "order by time asc " + "limit 1")
					.bind("uuid", uuid).map(new TimelineItemMapper()).first();
		} else {
			return handle
					.createQuery("SELECT id, type, method, name, time, data, uuid " + "FROM " + timelineTable() + " "
							+ "where type = 'action' " + "order by time asc " + "limit 1")
					.bind("uuid", uuid).map(new TimelineItemMapper()).first();
		}
	}

	public ITimelineItem selectLastAction(Handle handle) {
		return handle
				.createQuery("SELECT id, type, method, name, time, data, uuid " + "FROM " + timelineTable() + " "
						+ "where type = 'action' " + "order by time desc " + "limit 1")
				.map(new TimelineItemMapper()).first();
	}

	public ITimelineItem selectTimelineItem(Handle handle, String uuid) {
		return handle.createQuery("SELECT id, type, method, name, time, data, uuid " + "FROM " + timelineTable() + " "
				+ "where uuid = :uuid").bind("uuid", uuid).map(new TimelineItemMapper()).first();
	}

}
