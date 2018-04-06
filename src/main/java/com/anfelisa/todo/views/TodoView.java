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

	private static TodoDao todoDao = new TodoDao();
	private static CustomTodoDao customTodoDao = new CustomTodoDao();

	public static BiConsumer<TodoData, Handle> create = (dataContainer, handle) -> {
		todoDao.insert(handle, dataContainer);
	};
	public static BiConsumer<TodoData, Handle> update = (dataContainer, handle) -> {
		customTodoDao.updateById(handle, dataContainer);
	};
	public static BiConsumer<TodoToggleData, Handle> toggle = (dataContainer, handle) -> {
		customTodoDao.toggleTodo(handle, dataContainer);
	};
	public static BiConsumer<ToggleAllData, Handle> toggleAll = (dataContainer, handle) -> {
		customTodoDao.toggleAll(handle, dataContainer);
	};
	public static BiConsumer<TodoIdData, Handle> delete = (dataContainer, handle) -> {
		todoDao.deleteById(handle, dataContainer.getId());
	};
	public static BiConsumer<ClearDoneData, Handle> clearDone = (dataContainer, handle) -> {
		customTodoDao.deleteDone(handle);
	};

}

/* S.D.G. */
