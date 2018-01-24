package com.anfelisa.ace.scenario;

import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.Update;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
@JsonIgnoreType
public class ScenarioDao {
	
	public Integer insert(Handle handle, IScenarioModel scenarioModel) {
		Query<Map<String, Object>> statement = handle.createQuery("INSERT INTO public.scenario (description, timeline, creator, createddatetime, updateddatetime, serverversion, clientversion, device) VALUES (:description, :timeline, :creator, :createddatetime, :updateddatetime, :serverversion, :clientversion, :device) RETURNING id");
		statement.bind("description",  scenarioModel.getDescription() );
		statement.bind("timeline",  scenarioModel.getTimeline() );
		statement.bind("creator",  scenarioModel.getCreator() );
		statement.bind("createddatetime",  scenarioModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  scenarioModel.getUpdatedDateTime() );
		statement.bind("serverversion",  scenarioModel.getServerVersion() );
		statement.bind("clientversion",  scenarioModel.getClientVersion() );
		statement.bind("device",  scenarioModel.getDevice() );
		Map<String, Object> first = statement.first();
		return (Integer) first.get("id");
	}
	
	
	public void updateById(Handle handle, IScenarioModel scenarioModel) {
		Update statement = handle.createStatement("UPDATE public.scenario SET description = :description, timeline = :timeline, creator = :creator, createddatetime = :createddatetime, updateddatetime = :updateddatetime, serverversion = :serverversion, clientversion = :clientversion, device = :device WHERE id = :id");
		statement.bind("description",  scenarioModel.getDescription() );
		statement.bind("timeline",  scenarioModel.getTimeline() );
		statement.bind("creator",  scenarioModel.getCreator() );
		statement.bind("createddatetime",  scenarioModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  scenarioModel.getUpdatedDateTime() );
		statement.bind("serverversion",  scenarioModel.getServerVersion() );
		statement.bind("clientversion",  scenarioModel.getClientVersion() );
		statement.bind("device",  scenarioModel.getDevice() );
		statement.bind("id",  scenarioModel.getId()  );
		statement.execute();
	}

	public void deleteById(Handle handle, Integer id) {
		Update statement = handle.createStatement("DELETE FROM public.scenario WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public IScenarioModel selectById(Handle handle, Integer id) {
		return handle.createQuery("SELECT id, description, timeline, creator, createddatetime, updateddatetime, serverversion, clientversion, device FROM public.scenario WHERE id = :id")
			.bind("id", id)
			.map(new ScenarioMapper())
			.first();
	}
	
	public List<IScenarioModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, description, timeline, creator, createddatetime, updateddatetime, serverversion, clientversion, device FROM public.scenario")
			.map(new ScenarioMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createStatement("TRUNCATE public.scenario");
		statement.execute();
		statement = handle.createStatement("ALTER SEQUENCE public.scenario_id_seq RESTART");
		statement.execute();
	}

}

/*       S.D.G.       */

