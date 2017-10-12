package com.anfelisa.todo.models;

import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.Update;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import com.anfelisa.ace.encryption.EncryptionService;

@SuppressWarnings("all")
@JsonIgnoreType
public class TodoDao {
	
	public void create(Handle handle) {
		handle.execute("CREATE TABLE IF NOT EXISTS public.todo (id integer NOT NULL  , description character varying NOT NULL  , done boolean NOT NULL  , createddatetime timestamp with time zone  , updateddatetime timestamp with time zone  , CONSTRAINT todo_pkey PRIMARY KEY (id), CONSTRAINT todo_id_unique UNIQUE (id))");
	}
	
	public Integer insert(Handle handle, ITodoModel todoModel) {
		Query<Map<String, Object>> statement = handle.createQuery("INSERT INTO public.todo (id, description, done, createddatetime, updateddatetime) VALUES ( (SELECT COALESCE(MAX(id),0) + 1 FROM public.todo), :description, :done, :createddatetime, :updateddatetime) RETURNING id");
		statement.bind("description",  todoModel.getDescription() );
		statement.bind("done",  todoModel.getDone() );
		statement.bind("createddatetime",  todoModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  todoModel.getUpdatedDateTime() );
		Map<String, Object> first = statement.first();
		return (Integer) first.get("id");
	}
	
	
	public void updateById(Handle handle, ITodoModel todoModel) {
		Update statement = handle.createStatement("UPDATE public.todo SET id = :id, description = :description, done = :done, createddatetime = :createddatetime, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id",  todoModel.getId() );
		statement.bind("description",  todoModel.getDescription() );
		statement.bind("done",  todoModel.getDone() );
		statement.bind("createddatetime",  todoModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  todoModel.getUpdatedDateTime() );
		statement.execute();
	}

	public void deleteById(Handle handle, Integer id) {
		Update statement = handle.createStatement("DELETE FROM public.todo WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public ITodoModel selectById(Handle handle, Integer id) {
		return handle.createQuery("SELECT * FROM public.todo WHERE id = :id")
			.bind("id", id)
			.map(new TodoMapper())
			.first();
	}
	
	public List<ITodoModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT * FROM public.todo")
			.map(new TodoMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createStatement("TRUNCATE public.todo");
		statement.execute();
	}

}

/*       S.D.G.       */
