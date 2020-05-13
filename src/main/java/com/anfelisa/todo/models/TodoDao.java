package com.anfelisa.todo.models;

import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.statement.PreparedBatch;
import org.jdbi.v3.core.statement.Update;
import org.joda.time.DateTime;

import com.anfelisa.todo.data.IToggleAllData;

import de.acegen.PersistenceHandle;

public class TodoDao extends AbstractTodoDao {
	public void toggleTodo(PersistenceHandle handle, ITodoToggleModel model) {
		Update statement = handle.getHandle()
				.createUpdate("UPDATE public.todo SET done = :done, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id", model.getId());
		statement.bind("done", model.getDone());
		statement.bind("updateddatetime", model.getUpdatedDateTime());
		statement.execute();
	}

	public void updateById(PersistenceHandle handle, ITodoModel todoModel) {
		Update statement = handle.getHandle().createUpdate(
				"UPDATE public.todo SET description = :description, updateddatetime = :updateddatetime WHERE id = :id");
		statement.bind("id", todoModel.getId());
		statement.bind("description", todoModel.getDescription());
		statement.bind("updateddatetime", todoModel.getUpdatedDateTime());
		statement.execute();
	}

	public List<ITodoModel> selectAllOrderedByCreatedDate(PersistenceHandle handle) {
		return handle.getHandle().createQuery("SELECT * FROM public.todo order by createddatetime")
				.map(new TodoMapper()).list();
	}

	public void deleteDone(PersistenceHandle handle) {
		Update statement = handle.getHandle().createUpdate("DELETE FROM public.todo WHERE done = true");
		statement.execute();
	}

	public List<ITodoModel> selectDoneOrderedByCreatedDate(PersistenceHandle handle) {
		return handle.getHandle().createQuery("SELECT * FROM public.todo WHERE done = true order by createddatetime")
				.map(new TodoMapper()).list();
	}

	public List<ITodoModel> selectOpenOrderedByCreatedDate(PersistenceHandle handle) {
		return handle.getHandle().createQuery("SELECT * FROM public.todo WHERE done = false order by createddatetime")
				.map(new TodoMapper()).list();
	}

	public void toggleAll(PersistenceHandle handle, IToggleAllData dataContainer) {
		List<ITodoModel> todos = dataContainer.getTodosToBeToggled();
		List<String> idList = todos.stream().map(ITodoModel::getId).collect(Collectors.toList());
		Update statement = handle.getHandle().createUpdate(
				"UPDATE public.todo SET done = :done, updateddatetime = :updateddatetime where id in (<idlist>)");
		statement.bind("done", dataContainer.getDone());
		statement.bindList("idlist", idList);
		statement.bind("updateddatetime", dataContainer.getUpdatedDateTime());
		statement.execute();
	}

	public Integer insert(PersistenceHandle handle, DateTime now) {

		PreparedBatch pb = handle.getHandle().prepareBatch(
				"INSERT INTO public.todo (id, description, done, createddatetime, updateddatetime) VALUES ( :id, :description, :done, :createddatetime, :updateddatetime) RETURNING id");
		for (int i = 0; i < 1000000; i++) {
			if (i % 1000 == 0) {
				System.out.println("i " + i);
			}
			pb.add().bind("id", i + 2).bind("description", "TODO" + i).bind("done", false).bind("createddatetime", now)
					.bind("updateddatetime", now);
		}
		System.out.println("prepare data");
		return pb.execute().length;
	}

}

/* S.D.G. */
