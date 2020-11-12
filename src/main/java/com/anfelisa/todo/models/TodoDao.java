package com.anfelisa.todo.models;

import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.statement.Update;

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

	public List<ITodoModel> selectAllOrderedByCreatedDate(PersistenceHandle handle, String categoryId) {
		return handle.getHandle()
				.createQuery("SELECT * FROM public.todo where categoryid = :categoryid order by createddatetime")
				.bind("categoryid", categoryId).map(new TodoMapper()).list();
	}

	public void deleteDone(PersistenceHandle handle, String categoryId) {
		Update statement = handle.getHandle().createUpdate("DELETE FROM public.todo WHERE done = true and categoryid = :categoryid")
				.bind("categoryid", categoryId);
		statement.execute();
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

	public List<ITodoModel> selectAllOfCategory(PersistenceHandle handle, String categoryId) {
		return handle.getHandle().createQuery(
				"SELECT id, description, done, createddatetime, updateddatetime, categoryid FROM \"todo\" WHERE categoryid = :categoryid")
				.bind("categoryid", categoryId)
				.map(new TodoMapper()).list();
	}

}

/* S.D.G. */
