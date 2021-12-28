/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.statement.StatementContext;

import de.acegen.AbstractMapper;

public class TodoListMapper extends AbstractMapper<ITodoListModel> {
	
	public ITodoListModel map(ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoListModel(
			null,
			this.mapToString(r, "categoryId")
		);
	}
}



/******* S.D.G. *******/



