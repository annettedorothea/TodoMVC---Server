package com.anfelisa.ace;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.anfelisa.ace.encryption.EncryptionService;

public class TimelineItemMapper implements ResultSetMapper<ITimelineItem> {
	
	public ITimelineItem map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		DateTime time = DateTime.parse(r.getString("time"), fmt);
		return new TimelineItem(
			r.getString("type"),
			r.getString("method"),
			r.getString("name"),
			time,
			EncryptionService.decrypt(r.getString("data")),
			r.getString("uuid")
		);
	}
}
