package com.anfelisa.todo.models;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public class AbstractTodoDao {
	
	public void insert(Handle handle, ITodoModel todoModel) {
		Update statement = handle.createUpdate("INSERT INTO todo (id, description, done, createddatetime, updateddatetime) VALUES (:id, :description, :done, :createddatetime, :updateddatetime)");
		statement.bind("id",  todoModel.getId() );
		statement.bind("description",  todoModel.getDescription() );
		statement.bind("done",  todoModel.getDone() );
		statement.bind("createddatetime",  todoModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  todoModel.getUpdatedDateTime() );
		statement.execute();
	}
	
	
	public void updateById(Handle handle, ITodoModel todoModel) {
		Update statement = handle.createUpdate("UPDATE todo SET id = :id, description = :description, done = :done, createddatetime = :createddatetime, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id",  todoModel.getId() );
		statement.bind("description",  todoModel.getDescription() );
		statement.bind("done",  todoModel.getDone() );
		statement.bind("createddatetime",  todoModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  todoModel.getUpdatedDateTime() );
		statement.bind("id",  todoModel.getId()  );
		statement.execute();
	}

	public void deleteById(Handle handle, String id) {
		Update statement = handle.createUpdate("DELETE FROM todo WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public ITodoModel selectById(Handle handle, String id) {
		Optional<ITodoModel> optional = handle.createQuery("SELECT id, description, done, createddatetime, updateddatetime FROM todo WHERE id = :id")
			.bind("id", id)
			.map(new TodoMapper())
			.findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
	public List<ITodoModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, description, done, createddatetime, updateddatetime FROM todo")
			.map(new TodoMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createUpdate("TRUNCATE TABLE todo");
		statement.execute();
	}

}

/*       S.D.G.       */
