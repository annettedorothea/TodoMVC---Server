/********************************************************************************
 * generated by de.acegen 1.3.0
 ********************************************************************************/




package com.anfelisa.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.statement.StatementContext;

import de.acegen.AbstractMapper;

public class ClearDoneMapper extends AbstractMapper<IClearDoneModel> {
	
	public IClearDoneModel map(ResultSet r, StatementContext ctx) throws SQLException {
		return new ClearDoneModel(
			this.mapToString(r, "categoryId")
		);
	}
}



/******* S.D.G. *******/



