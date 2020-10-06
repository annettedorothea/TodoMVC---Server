/********************************************************************************
 * generated by de.acegen 0.9.10
 ********************************************************************************/




package com.anfelisa.todo.models;

import de.acegen.PersistenceHandle;
import org.jdbi.v3.core.statement.Update;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("all")
public class AbstractTodoDao {
	
	public void insert(PersistenceHandle handle, ITodoModel todoModel) {
		Update statement = handle.getHandle().createUpdate("INSERT INTO public.todo (id, description, done, createddatetime, updateddatetime) VALUES (:id, :description, :done, :createddatetime, :updateddatetime)");
		statement.bind("id",  todoModel.getId() );
		statement.bind("description",  todoModel.getDescription() );
		statement.bind("done",  todoModel.getDone() );
		statement.bind("createddatetime",  todoModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  todoModel.getUpdatedDateTime() );
		statement.execute();
	}
	
	
	public void updateById(PersistenceHandle handle, ITodoModel todoModel) {
		Update statement = handle.getHandle().createUpdate("UPDATE public.todo SET id = :id, description = :description, done = :done, createddatetime = :createddatetime, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id",  todoModel.getId() );
		statement.bind("description",  todoModel.getDescription() );
		statement.bind("done",  todoModel.getDone() );
		statement.bind("createddatetime",  todoModel.getCreatedDateTime() );
		statement.bind("updateddatetime",  todoModel.getUpdatedDateTime() );
		statement.bind("id",  todoModel.getId()  );
		statement.execute();
	}

	public void deleteById(PersistenceHandle handle, String id) {
		Update statement = handle.getHandle().createUpdate("DELETE FROM public.todo WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public ITodoModel selectById(PersistenceHandle handle, String id) {
		Optional<ITodoModel> optional = handle.getHandle().createQuery("SELECT id, description, done, createddatetime, updateddatetime FROM public.todo WHERE id = :id")
			.bind("id", id)
			.map(new TodoMapper())
			.findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
	public ITodoModel selectByPrimaryKey(PersistenceHandle handle, String id) {
		Optional<ITodoModel> optional = handle.getHandle().createQuery("SELECT id, description, done, createddatetime, updateddatetime FROM public.todo WHERE id = :id")
			.bind("id", id)
			.map(new TodoMapper())
			.findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
	public int filterAndCountBy(PersistenceHandle handle, Map<String, String> filterMap) {
		String sql = "SELECT count(*) FROM public.todo";
		if (filterMap != null) {
			int i = 0;
			for(String key : filterMap.keySet()) {
				if (i == 0) {
					sql += " WHERE " + key + " = '" + filterMap.get(key) + "'";
				} else {
					sql += " AND " + key + " = '" + filterMap.get(key) + "'";
				}
				i++;
			}
		}
		return handle.getHandle().createQuery(sql).mapTo(Integer.class).first();
	}

	public List<ITodoModel> selectAll(PersistenceHandle handle) {
		return handle.getHandle().createQuery("SELECT id, description, done, createddatetime, updateddatetime FROM public.todo")
			.map(new TodoMapper())
			.list();
	}

	public void truncate(PersistenceHandle handle) {
		Update statement = handle.getHandle().createUpdate("TRUNCATE TABLE public.todo CASCADE");
		statement.execute();
	}

}



/******* S.D.G. *******/



