package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
public class TodoMapper implements ResultSetMapper<ITodoModel> {
	
	public ITodoModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoModel(
			r.getString("id"),
			r.getString("description"),
			r.getBoolean("done"),
			r.getTimestamp("createdDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("createdDateTime")) : null,
			r.getTimestamp("updatedDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("updatedDateTime")) : null
		);
	}
}

/*       S.D.G.       */
