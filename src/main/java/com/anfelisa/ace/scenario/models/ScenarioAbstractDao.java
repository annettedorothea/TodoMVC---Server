package com.anfelisa.ace.scenario.models;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.Update;

import com.anfelisa.ace.scenario.IScenarioModel;
import com.anfelisa.ace.scenario.ScenarioMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@SuppressWarnings("all")
@JsonIgnoreType
public class ScenarioAbstractDao {
	
	public IScenarioModel selectById(Handle handle, Integer id) {
		return handle.createQuery("")
			.bind("id", id)
			.map(new ScenarioMapper())
			.first();
	}
	
	public List<IScenarioAbstractModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, description, timeline, creator, createddatetime, updateddatetime, serverversion, clientversion, device, (SELECT sr.result FROM public.scenarioresult sr WHERE sr.scenarioid = s.id order by sr.createddatetime desc limit 1) as lastresult FROM public.scenario s;")
			.map(new ScenarioAbstractMapper())
			.list();
	}

}

/*       S.D.G.       */

