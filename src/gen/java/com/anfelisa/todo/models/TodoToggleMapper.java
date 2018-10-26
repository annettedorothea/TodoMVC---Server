package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

@SuppressWarnings("all")
public class TodoToggleMapper implements RowMapper<ITodoToggleModel> {
	
	public ITodoToggleModel map(ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoToggleModel(
			r.getString("id"),
			r.getTimestamp("updatedDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("updatedDateTime")) : null
		);
	}
}

/*       S.D.G.       */
