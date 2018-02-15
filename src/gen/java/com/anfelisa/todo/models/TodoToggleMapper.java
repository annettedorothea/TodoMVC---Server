package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
public class TodoToggleMapper implements ResultSetMapper<ITodoToggleModel> {
	
	public ITodoToggleModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoToggleModel(
			r.getInt("id"),
			r.getTimestamp("updatedDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("updatedDateTime")) : null
		);
	}
}

/*       S.D.G.       */
