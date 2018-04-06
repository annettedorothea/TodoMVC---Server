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
	
	public void insert(Handle handle, ITodoModel todoModel) {
		Update statement = handle.createStatement("INSERT INTO public.todo (id, description, done, createddatetime, updateddatetime) VALUES (:id, :description, :done, :createddatetime, :updateddatetime)");
		statement.bind("id",  todoModel.getId() );
		statement.bind("description",  todoModel.getDescription() );
		statement.bind("done",  todoModel.getDone() );
		statement.bind("createddatetime",  todoModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  todoModel.getUpdatedDateTime() );
		statement.execute();
	}
	
	
	public void updateById(Handle handle, ITodoModel todoModel) {
		Update statement = handle.createStatement("UPDATE public.todo SET id = :id, description = :description, done = :done, createddatetime = :createddatetime, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id",  todoModel.getId() );
		statement.bind("description",  todoModel.getDescription() );
		statement.bind("done",  todoModel.getDone() );
		statement.bind("createddatetime",  todoModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  todoModel.getUpdatedDateTime() );
		statement.bind("id",  todoModel.getId()  );
		statement.execute();
	}

	public void deleteById(Handle handle, String id) {
		Update statement = handle.createStatement("DELETE FROM public.todo WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public ITodoModel selectById(Handle handle, String id) {
		return handle.createQuery("SELECT id, description, done, createddatetime, updateddatetime FROM public.todo WHERE id = :id")
			.bind("id", id)
			.map(new TodoMapper())
			.first();
	}
	
	public List<ITodoModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, description, done, createddatetime, updateddatetime FROM public.todo")
			.map(new TodoMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createStatement("TRUNCATE public.todo CASCADE");
		statement.execute();
	}

}

/*       S.D.G.       */
