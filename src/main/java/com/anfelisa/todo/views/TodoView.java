package com.anfelisa.todo.views;

import java.util.function.BiConsumer;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.todo.data.ClearDoneData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.data.TodoIdData;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.data.ToggleAllData;

@SuppressWarnings("all")
public class TodoView {

	private IDaoProvider daoProvider;

	public TodoView(IDaoProvider daoProvider) {
		super();
		this.daoProvider = daoProvider;
	}

	public BiConsumer<TodoData, Handle> create = (dataContainer, handle) -> {
		daoProvider.getTodoDao().insert(handle, dataContainer);
	};
	public BiConsumer<TodoData, Handle> update = (dataContainer, handle) -> {
		daoProvider.getCustomTodoDao().updateById(handle, dataContainer);
	};
	public BiConsumer<TodoToggleData, Handle> toggle = (dataContainer, handle) -> {
		daoProvider.getCustomTodoDao().toggleTodo(handle, dataContainer);
	};
	public BiConsumer<ToggleAllData, Handle> toggleAll = (dataContainer, handle) -> {
		daoProvider.getCustomTodoDao().toggleAll(handle, dataContainer);
	};
	public BiConsumer<TodoIdData, Handle> delete = (dataContainer, handle) -> {
		daoProvider.getTodoDao().deleteById(handle, dataContainer.getId());
	};
	public BiConsumer<ClearDoneData, Handle> clearDone = (dataContainer, handle) -> {
		daoProvider.getCustomTodoDao().deleteDone(handle);
	};

}

/* S.D.G. */
