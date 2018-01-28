package com.anfelisa.ace.scenario.models;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.Update;

import com.anfelisa.ace.scenario.IScenarioResultModel;
import com.anfelisa.ace.scenario.ScenarioResultMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@SuppressWarnings("all")
@JsonIgnoreType
public class CustomScenarioResultDao {

	public List<IScenarioResultModel> selectAll(Handle handle, Integer scenarioId) {

		return handle.createQuery(
				"SELECT id, scenarioid, description, timeline, executor, createddatetime, serverversion, clientversion, device, result, e2e FROM public.scenarioresult WHERE scenarioid = :scenarioid")
				.bind("scenarioid", scenarioId)
				.map(new ScenarioResultMapper()).list();
	}

}

/* S.D.G. */
