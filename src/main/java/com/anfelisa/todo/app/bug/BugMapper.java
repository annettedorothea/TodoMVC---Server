package com.anfelisa.todo.app.bug;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
public class BugMapper implements ResultSetMapper<IBugModel> {
	
	public IBugModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new BugModel(
			r.getInt("id"),
			r.getString("description"),
			r.getString("data"),
			r.getString("reporter"),
			r.getBoolean("resolved"),
			r.getTimestamp("createdDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("createdDateTime")) : null,
			r.getTimestamp("updatedDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("updatedDateTime")) : null
		);
	}
}

/*       S.D.G.       */
