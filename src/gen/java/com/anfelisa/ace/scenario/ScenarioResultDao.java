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
public class ScenarioResultDao {
	
	public Integer insert(Handle handle, IScenarioResultModel scenarioResultModel) {
		Query<Map<String, Object>> statement = handle.createQuery("INSERT INTO public.scenarioresult (scenarioid, description, timeline, executor, createddatetime, serverversion, clientversion, device, result, e2e) VALUES (:scenarioid, :description, :timeline, :executor, :createddatetime, :serverversion, :clientversion, :device, :result, :e2e) RETURNING id");
		statement.bind("scenarioid",  scenarioResultModel.getScenarioId() );
		statement.bind("description",  scenarioResultModel.getDescription() );
		statement.bind("timeline",  scenarioResultModel.getTimeline() );
		statement.bind("executor",  scenarioResultModel.getExecutor() );
		statement.bind("createddatetime",  scenarioResultModel.getCreatedDateTime() );
		statement.bind("serverversion",  scenarioResultModel.getServerVersion() );
		statement.bind("clientversion",  scenarioResultModel.getClientVersion() );
		statement.bind("device",  scenarioResultModel.getDevice() );
		statement.bind("result",  scenarioResultModel.getResult() );
		statement.bind("e2e",  scenarioResultModel.getE2E() );
		Map<String, Object> first = statement.first();
		return (Integer) first.get("id");
	}
	
	
	public void updateById(Handle handle, IScenarioResultModel scenarioResultModel) {
		Update statement = handle.createStatement("UPDATE public.scenarioresult SET scenarioid = :scenarioid, description = :description, timeline = :timeline, executor = :executor, createddatetime = :createddatetime, serverversion = :serverversion, clientversion = :clientversion, device = :device, result = :result, e2e = :e2e WHERE id = :id");
		statement.bind("scenarioid",  scenarioResultModel.getScenarioId() );
		statement.bind("description",  scenarioResultModel.getDescription() );
		statement.bind("timeline",  scenarioResultModel.getTimeline() );
		statement.bind("executor",  scenarioResultModel.getExecutor() );
		statement.bind("createddatetime",  scenarioResultModel.getCreatedDateTime() );
		statement.bind("serverversion",  scenarioResultModel.getServerVersion() );
		statement.bind("clientversion",  scenarioResultModel.getClientVersion() );
		statement.bind("device",  scenarioResultModel.getDevice() );
		statement.bind("result",  scenarioResultModel.getResult()  );
		statement.bind("e2e",  scenarioResultModel.getE2E()  );
		statement.bind("id",  scenarioResultModel.getId()  );
		statement.execute();
	}

	public void deleteById(Handle handle, Integer id) {
		Update statement = handle.createStatement("DELETE FROM public.scenarioresult WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public IScenarioResultModel selectById(Handle handle, Integer id) {
		return handle.createQuery("SELECT id, scenarioid, description, timeline, executor, createddatetime, serverversion, clientversion, device, result, e2e FROM public.scenarioresult WHERE id = :id")
			.bind("id", id)
			.map(new ScenarioResultMapper())
			.first();
	}
	
	public List<IScenarioResultModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, scenarioid, description, timeline, executor, createddatetime, serverversion, clientversion, device, result, e2e FROM public.scenarioresult")
			.map(new ScenarioResultMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createStatement("TRUNCATE public.scenarioresult");
		statement.execute();
		statement = handle.createStatement("ALTER SEQUENCE public.scenarioresult_id_seq RESTART");
		statement.execute();
	}

}

/*       S.D.G.       */

