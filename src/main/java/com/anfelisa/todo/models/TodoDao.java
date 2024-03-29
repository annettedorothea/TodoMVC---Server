package com.anfelisa.todo.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jdbi.v3.core.statement.Update;

import com.anfelisa.todo.data.IToggleAllData;

import de.acegen.PersistenceHandle;

public class TodoDao extends AbstractTodoDao {
	
	public void toggleTodo(PersistenceHandle handle, ITodoToggleModel model) {
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("id", model.getId());
		params.put("done", model.getDone());
		params.put("updateddatetime", model.getUpdatedDateTime());
		update(handle, "UPDATE todo SET done = :done, updateddatetime = :updateddatetime WHERE id = :id", params);
	}

	public void updateById(PersistenceHandle handle, ITodoModel todoModel) {
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("id", todoModel.getId());
		params.put("description", todoModel.getDescription());
		params.put("updateddatetime", todoModel.getUpdatedDateTime());
		update(handle, "UPDATE todo SET description = :description, updateddatetime = :updateddatetime WHERE id = :id", params);
	}

	public List<ITodoItemModel> selectAllOrderedByCreatedDate(PersistenceHandle handle, String categoryId) {
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("categoryid", categoryId);
		return selectList(handle, "SELECT id, description, done FROM todo where categoryid = :categoryid order by createddatetime", params, new TodoItemMapper());
	}

	public void deleteDone(PersistenceHandle handle, String categoryId) {
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("categoryid", categoryId);
		update(handle, "DELETE FROM todo WHERE done = true and categoryid = :categoryid", params);
	}

	public void toggleAll(PersistenceHandle handle, IToggleAllData dataContainer) {
		List<ITodoModel> todos = dataContainer.getTodosToBeToggled();
		List<String> idList = todos.stream().map(ITodoModel::getId).collect(Collectors.toList());
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("done", dataContainer.getDone());
		params.put("idlist", idList);
		params.put("updateddatetime", dataContainer.getUpdatedDateTime());
		update(handle, "UPDATE todo SET done = :done, updateddatetime = :updateddatetime where id in (<idlist>)", params);
	}

	public List<ITodoModel> selectAllOfCategory(PersistenceHandle handle, String categoryId) {
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("categoryid", categoryId);
		return selectList(handle, "SELECT id, description, done, createddatetime, updateddatetime, categoryid FROM todo WHERE categoryid = :categoryid", params, new TodoMapper());
	}

	@Override
	public void truncate(PersistenceHandle handle) {
		Update statement = handle.getHandle().createUpdate("DELETE FROM \"todo\"");
		statement.execute();
	}

}

/* S.D.G. */
