package com.anfelisa.todo.app.scenario;

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
	
	public void create(Handle handle) {
		handle.execute("CREATE TABLE IF NOT EXISTS public.scenario (id integer NOT NULL  , description character varying NOT NULL  , data character varying NOT NULL  , createddatetime timestamp with time zone  , CONSTRAINT scenario_pkey PRIMARY KEY (id), CONSTRAINT scenario_id_unique UNIQUE (id))");
	}
	
	public Integer insert(Handle handle, IScenarioModel scenarioModel) {
		Query<Map<String, Object>> statement = handle.createQuery("INSERT INTO public.scenario (id, description, data, createddatetime) VALUES ( (SELECT COALESCE(MAX(id),0) + 1 FROM public.scenario), :description, :data, :createddatetime) RETURNING id");
		statement.bind("description",  scenarioModel.getDescription() );
		statement.bind("data",  scenarioModel.getData() );
		statement.bind("createddatetime",  scenarioModel.getCreatedDateTime() );
		Map<String, Object> first = statement.first();
		return (Integer) first.get("id");
	}
	
	
	public void updateById(Handle handle, IScenarioModel scenarioModel) {
		Update statement = handle.createStatement("UPDATE public.scenario SET id = :id, description = :description, data = :data, createddatetime = :createddatetime WHERE id = :id");
		statement.bind("id",  scenarioModel.getId() );
		statement.bind("description",  scenarioModel.getDescription() );
		statement.bind("data",  scenarioModel.getData() );
		statement.bind("createddatetime",  scenarioModel.getCreatedDateTime() );
		statement.execute();
	}

	public void deleteById(Handle handle, Integer id) {
		Update statement = handle.createStatement("DELETE FROM public.scenario WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public IScenarioModel selectById(Handle handle, Integer id) {
		return handle.createQuery("SELECT * FROM public.scenario WHERE id = :id")
			.bind("id", id)
			.map(new ScenarioMapper())
			.first();
	}
	
	public List<IScenarioModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT * FROM public.scenario")
			.map(new ScenarioMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createStatement("TRUNCATE public.scenario");
		statement.execute();
	}

}

/*       S.D.G.       */
