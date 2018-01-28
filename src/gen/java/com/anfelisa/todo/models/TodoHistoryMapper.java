package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
public class TodoHistoryMapper implements ResultSetMapper<ITodoHistoryModel> {
	
	public ITodoHistoryModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoHistoryModel(
			r.getInt("id"),
			r.getInt("todoId"),
			r.getString("description"),
			r.getBoolean("done"),
			r.getBoolean("deleted"),
			r.getTimestamp("changedTime") != null ? new org.joda.time.DateTime(r.getTimestamp("changedTime")) : null,
			r.getString("action")
		);
	}
}

/*       S.D.G.       */
