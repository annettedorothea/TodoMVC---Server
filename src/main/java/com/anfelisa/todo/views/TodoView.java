package com.anfelisa.todo.views;

import java.util.function.BiConsumer;

import org.skife.jdbi.v2.Handle;

import com.anfelisa.todo.data.ClearDoneData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.data.TodoIdData;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.data.ToggleAllData;
import com.anfelisa.todo.models.CustomTodoDao;
import com.anfelisa.todo.models.TodoDao;

@SuppressWarnings("all")
public class TodoView {

	private TodoDao todoDao = new TodoDao();
	private CustomTodoDao customTodoDao = new CustomTodoDao();

	public BiConsumer<TodoData, Handle> create = (dataContainer, handle) -> {
		todoDao.insert(handle, dataContainer);
	};
	public BiConsumer<TodoData, Handle> update = (dataContainer, handle) -> {
		customTodoDao.updateById(handle, dataContainer);
	};
	public BiConsumer<TodoToggleData, Handle> toggle = (dataContainer, handle) -> {
		customTodoDao.toggleTodo(handle, dataContainer);
	};
	public BiConsumer<ToggleAllData, Handle> toggleAll = (dataContainer, handle) -> {
		customTodoDao.toggleAll(handle, dataContainer);
	};
	public BiConsumer<TodoIdData, Handle> delete = (dataContainer, handle) -> {
		todoDao.deleteById(handle, dataContainer.getId());
	};
	public BiConsumer<ClearDoneData, Handle> clearDone = (dataContainer, handle) -> {
		customTodoDao.deleteDone(handle);
	};

}

/* S.D.G. */
