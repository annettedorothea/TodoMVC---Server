package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class TodoListMapper implements RowMapper<ITodoListModel> {
	
	public ITodoListModel map(ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoListModel(
			null
		);
	}
}

/*       S.D.G.       */
