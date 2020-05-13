package com.anfelisa.todo.views;

import com.anfelisa.todo.data.IClearDoneData;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.ITodoIdData;
import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.data.IToggleAllData;

import de.acegen.IDaoProvider;
import de.acegen.PersistenceHandle;

public class TodoView implements ITodoView {

	private IDaoProvider daoProvider;

	public TodoView(IDaoProvider daoProvider) {
		super();
		this.daoProvider = daoProvider;
	}

	public void create(ITodoData data, PersistenceHandle handle) {
		daoProvider.getTodoDao().insert(handle, data);
	}

	public void toggle(ITodoToggleData data, PersistenceHandle handle) {
		daoProvider.getTodoDao().toggleTodo(handle, data);
	}

	public void toggleAll(IToggleAllData data, PersistenceHandle handle) {
		daoProvider.getTodoDao().toggleAll(handle, data);
	}

	public void delete(ITodoIdData data, PersistenceHandle handle) {
		daoProvider.getTodoDao().deleteById(handle, data.getId());
	}

	public void update(ITodoData data, PersistenceHandle handle) {
		daoProvider.getTodoDao().updateById(handle, data);
	}

	public void clearDone(IClearDoneData data, PersistenceHandle handle) {
		daoProvider.getTodoDao().deleteDone(handle);
	}

}

/* S.D.G. */
