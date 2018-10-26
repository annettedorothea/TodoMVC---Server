package com.anfelisa.ace;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class TimelineItemMapper implements RowMapper<ITimelineItem> {
	
	public ITimelineItem map(ResultSet r, StatementContext ctx) throws SQLException {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		DateTime time = DateTime.parse(r.getString("time"), fmt);
		return new TimelineItem(
			r.getString("type"),
			r.getString("method"),
			r.getString("name"),
			time,
			r.getString("data"),
			r.getString("uuid")
		);
	}
}
