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
	
	public Integer insert(Handle handle, IScenarioModel scenarioModel) {
		Query<Map<String, Object>> statement = handle.createQuery("INSERT INTO public.scenario (description, data, createddatetime) VALUES ( :description, :data, :createddatetime) RETURNING id");
		statement.bind("description",  scenarioModel.getDescription() );
		statement.bind("data",  scenarioModel.getData() );
		statement.bind("createddatetime",  scenarioModel.getCreatedDateTime() );
		Map<String, Object> first = statement.first();
		return (Integer) first.get("id");
	}
	
	
	public void updateById(Handle handle, IScenarioModel scenarioModel) {
		Update statement = handle.createStatement("UPDATE public.scenario SET description = :description, data = :data, createddatetime = :createddatetime WHERE id = :id");
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
		return handle.createQuery("SELECT id, description, data, createddatetime FROM public.scenario WHERE id = :id")
			.bind("id", id)
			.map(new ScenarioMapper())
			.first();
	}
	
	public List<IScenarioModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, description, data, createddatetime FROM public.scenario order by createddatetime")
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
