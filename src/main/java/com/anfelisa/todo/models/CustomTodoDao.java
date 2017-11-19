package com.anfelisa.todo.models;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.PreparedBatch;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import com.anfelisa.ace.encryption.EncryptionService;
import com.anfelisa.todo.data.ToggleAllData;

@SuppressWarnings("all")
@JsonIgnoreType
public class CustomTodoDao {

	public void toggleTodo(Handle handle, ITodoToggleModel model) {
		Update statement = handle
				.createStatement("UPDATE public.todo SET done = ((SELECT done from public.todo WHERE id = :id) = false), updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id", model.getId());
		statement.bind("updateddatetime", model.getUpdatedDateTime());
		statement.execute();
	}

	public void updateById(Handle handle, ITodoModel todoModel) {
		Update statement = handle.createStatement(
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
		Update statement = handle.createStatement("DELETE FROM public.todo WHERE done = true");
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
		Update statement = handle.createStatement(
				"UPDATE public.todo SET done = :done, updateddatetime = :updateddatetime");
		statement.bind("done", dataContainer.getDone());
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
