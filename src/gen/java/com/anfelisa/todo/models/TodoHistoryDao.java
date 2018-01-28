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
public class TodoHistoryDao {
	
	public Integer insert(Handle handle, ITodoHistoryModel todoHistoryModel) {
		Query<Map<String, Object>> statement = handle.createQuery("INSERT INTO public.todohistory (todoid, description, done, deleted, changedtime, action) VALUES (:todoid, :description, :done, :deleted, :changedtime, :action) RETURNING id");
		statement.bind("todoid",  todoHistoryModel.getTodoId() );
		statement.bind("description",  todoHistoryModel.getDescription() );
		statement.bind("done",  todoHistoryModel.getDone() );
		statement.bind("deleted",  todoHistoryModel.getDeleted() );
		statement.bind("changedtime",  todoHistoryModel.getChangedTime() );
		statement.bind("action",  todoHistoryModel.getAction() );
		Map<String, Object> first = statement.first();
		return (Integer) first.get("id");
	}
	
	
	public void updateById(Handle handle, ITodoHistoryModel todoHistoryModel) {
		Update statement = handle.createStatement("UPDATE public.todohistory SET todoid = :todoid, description = :description, done = :done, deleted = :deleted, changedtime = :changedtime, action = :action WHERE id = :id");
		statement.bind("todoid",  todoHistoryModel.getTodoId() );
		statement.bind("description",  todoHistoryModel.getDescription() );
		statement.bind("done",  todoHistoryModel.getDone() );
		statement.bind("deleted",  todoHistoryModel.getDeleted() );
		statement.bind("changedtime",  todoHistoryModel.getChangedTime() );
		statement.bind("action",  todoHistoryModel.getAction() );
		statement.bind("id",  todoHistoryModel.getId()  );
		statement.execute();
	}

	public void deleteById(Handle handle, Integer id) {
		Update statement = handle.createStatement("DELETE FROM public.todohistory WHERE id = :id");
		statement.bind("id", id);
		statement.execute();
	}

	public ITodoHistoryModel selectById(Handle handle, Integer id) {
		return handle.createQuery("SELECT id, todoid, description, done, deleted, changedtime, action FROM public.todohistory WHERE id = :id")
			.bind("id", id)
			.map(new TodoHistoryMapper())
			.first();
	}
	
	public List<ITodoHistoryModel> selectAll(Handle handle) {
		return handle.createQuery("SELECT id, todoid, description, done, deleted, changedtime, action FROM public.todohistory")
			.map(new TodoHistoryMapper())
			.list();
	}

	public void truncate(Handle handle) {
		Update statement = handle.createStatement("TRUNCATE public.todohistory CASCADE");
		statement.execute();
		statement = handle.createStatement("ALTER SEQUENCE public.todohistory_id_seq RESTART");
		statement.execute();
	}

}

/*       S.D.G.       */
