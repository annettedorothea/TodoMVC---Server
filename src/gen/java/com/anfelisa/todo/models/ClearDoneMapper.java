/********************************************************************************
 * generated by de.acegen 1.0.5
 ********************************************************************************/




package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class ClearDoneMapper implements RowMapper<IClearDoneModel> {
	
	public IClearDoneModel map(ResultSet r, StatementContext ctx) throws SQLException {
		return new ClearDoneModel(
			r.getString("categoryId")
		);
	}
}



/******* S.D.G. *******/



