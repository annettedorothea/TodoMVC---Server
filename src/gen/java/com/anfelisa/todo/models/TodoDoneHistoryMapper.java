package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

@SuppressWarnings("all")
public class TodoDoneHistoryMapper implements RowMapper<ITodoDoneHistoryModel> {
	
	public ITodoDoneHistoryModel map(ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoDoneHistoryModel(
			r.getString("id"),
			r.getString("description"),
			r.getTimestamp("doneDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("doneDateTime")) : null
		);
	}
}

/*       S.D.G.       */
