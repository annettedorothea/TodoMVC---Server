package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
public class TodoIdMapper implements ResultSetMapper<ITodoIdModel> {
	
	public ITodoIdModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoIdModel(
			r.getInt("id")
		);
	}
}

/*       S.D.G.       */
