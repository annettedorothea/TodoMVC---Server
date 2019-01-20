package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class ToggleAllMapper implements RowMapper<IToggleAllModel> {
	
	public IToggleAllModel map(ResultSet r, StatementContext ctx) throws SQLException {
		return new ToggleAllModel(
			r.getBoolean("done"),
			null,
			r.getTimestamp("updatedDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("updatedDateTime")) : null
		);
	}
}

/*       S.D.G.       */
