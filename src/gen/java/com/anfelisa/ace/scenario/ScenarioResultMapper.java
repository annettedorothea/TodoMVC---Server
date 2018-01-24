package com.anfelisa.ace.scenario;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
public class ScenarioResultMapper implements ResultSetMapper<IScenarioResultModel> {
	
	public IScenarioResultModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new ScenarioResultModel(
			r.getInt("id"),
			r.getInt("scenarioId"),
			r.getString("description"),
			r.getString("timeline"),
			r.getString("executor"),
			r.getTimestamp("createdDateTime") != null ? new org.joda.time.DateTime(r.getTimestamp("createdDateTime")) : null,
			r.getString("serverVersion"),
			r.getString("clientVersion"),
			r.getString("device"),
			r.getBoolean("result"),
			r.getBoolean("e2e")
		);
	}
}

/*       S.D.G.       */
		
