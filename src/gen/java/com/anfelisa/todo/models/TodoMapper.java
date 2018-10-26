package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

@SuppressWarnings("all")
public class TodoMapper implements RowMapper<ITodoModel> {
	
	public ITodoModel map(ResultSet r, StatementContext ctx) throws SQLException {
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
