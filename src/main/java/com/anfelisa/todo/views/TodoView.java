package com.anfelisa.todo.views;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.todo.data.ClearDoneData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.data.TodoIdData;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.data.ToggleAllData;

public class TodoView implements ITodoView {

	private IDaoProvider daoProvider;

	public TodoView(IDaoProvider daoProvider) {
		super();
		this.daoProvider = daoProvider;
	}

	public void create(TodoData data, Handle handle) {
		daoProvider.getTodoDao().insert(handle, data);
	}

	public void toggle(TodoToggleData data, Handle handle) {
		daoProvider.getTodoDao().toggleTodo(handle, data);
	}

	public void toggleAll(ToggleAllData data, Handle handle) {
		daoProvider.getTodoDao().toggleAll(handle, data);
	}

	public void delete(TodoIdData data, Handle handle) {
		daoProvider.getTodoDao().deleteById(handle, data.getId());
	}

	public void update(TodoData data, Handle handle) {
		daoProvider.getTodoDao().updateById(handle, data);
	}

	public void clearDone(ClearDoneData data, Handle handle) {
		daoProvider.getTodoDao().deleteDone(handle);
	}

}

/* S.D.G. */
