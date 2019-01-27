package com.anfelisa.todo.views;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.todo.data.IClearDoneData;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.ITodoIdData;
import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.data.IToggleAllData;

public class TodoView implements ITodoView {

	private IDaoProvider daoProvider;

	public TodoView(IDaoProvider daoProvider) {
		super();
		this.daoProvider = daoProvider;
	}

	public void create(ITodoData data, Handle handle) {
		daoProvider.getTodoDao().insert(handle, data);
	}

	public void toggle(ITodoToggleData data, Handle handle) {
		daoProvider.getTodoDao().toggleTodo(handle, data);
	}

	public void toggleAll(IToggleAllData data, Handle handle) {
		daoProvider.getTodoDao().toggleAll(handle, data);
	}

	public void delete(ITodoIdData data, Handle handle) {
		daoProvider.getTodoDao().deleteById(handle, data.getId());
	}

	public void update(ITodoData data, Handle handle) {
		daoProvider.getTodoDao().updateById(handle, data);
	}

	public void clearDone(IClearDoneData data, Handle handle) {
		daoProvider.getTodoDao().deleteDone(handle);
	}

}

/* S.D.G. */
