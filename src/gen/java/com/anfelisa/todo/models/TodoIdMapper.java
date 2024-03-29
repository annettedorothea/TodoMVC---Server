/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.statement.StatementContext;

import de.acegen.AbstractMapper;

public class TodoIdMapper extends AbstractMapper<ITodoIdModel> {
	
	public ITodoIdModel map(ResultSet r, StatementContext ctx) throws SQLException {
		return new TodoIdModel(
			this.mapToString(r, "id")
		);
	}
}



/******* S.D.G. *******/



