package com.anfelisa.todo.models;

import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.jdbi.v3.core.statement.Update;
import org.joda.time.DateTime;

import com.anfelisa.todo.data.ToggleAllData;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@SuppressWarnings("all")
@JsonIgnoreType
public class CustomTodoDao {

	public void toggleTodo(Handle handle, ITodoToggleModel model) {
		Update statement = handle
				.createUpdate("UPDATE public.todo SET done = :done, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id", model.getId());
		statement.bind("done", model.getDone());
		statement.bind("updateddatetime", model.getUpdatedDateTime());
		statement.execute();
	}

	public void updateById(Handle handle, ITodoModel todoModel) {
		Update statement = handle.createUpdate(
				"UPDATE public.todo SET description = :description, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id", todoModel.getId());
		statement.bind("description", todoModel.getDescription());
		statement.bind("updateddatetime", todoModel.getUpdatedDateTime());
		statement.execute();
	}
	
	public List<ITodoModel> selectAllOrderedByCreatedDate(Handle handle) {
		return handle.createQuery("SELECT * FROM public.todo order by createddatetime")
			.map(new TodoMapper())
			.list();
	}

	public void deleteDone(Handle handle) {
		Update statement = handle.createUpdate("DELETE FROM public.todo WHERE done = true");
		statement.execute();
	}

	public List<ITodoModel> selectDoneOrderedByCreatedDate(Handle handle) {
		return handle.createQuery("SELECT * FROM public.todo WHERE done = true order by createddatetime")
				.map(new TodoMapper())
				.list();
	}

	public List<ITodoModel> selectOpenOrderedByCreatedDate(Handle handle) {
		return handle.createQuery("SELECT * FROM public.todo WHERE done = false order by createddatetime")
				.map(new TodoMapper())
				.list();
	}

	public void toggleAll(Handle handle, ToggleAllData dataContainer) {
		List<ITodoModel> todos = dataContainer.getTodosToBeToggled();
		List<String> idList = todos.stream().map(ITodoModel::getId).collect(Collectors.toList());
		Update statement = handle.createUpdate(
				"UPDATE public.todo SET done = :done, updateddatetime = :updateddatetime where id in (<idlist>)");
		statement.bind("done", dataContainer.getDone());
		statement.bindList("idlist", idList);
		statement.bind("updateddatetime", dataContainer.getUpdatedDateTime());
		statement.execute();
	}
	
	public Integer insert(Handle handle, DateTime now) {
		
		PreparedBatch pb = handle.prepareBatch("INSERT INTO public.todo (id, description, done, createddatetime, updateddatetime) VALUES ( :id, :description, :done, :createddatetime, :updateddatetime) RETURNING id");
		for (int i=0; i<1000000; i++) {
			if (i%1000 == 0) {
				System.out.println("i " + i);
			}
			 pb.add()
			 .bind("id", i+2)
			 .bind("description", "TODO" + i)
			 .bind("done", false)
			 .bind("createddatetime", now)
			 .bind("updateddatetime", now);
		}
		System.out.println("prepare data");
		return pb.execute().length;
	}
	


}

/* S.D.G. */
