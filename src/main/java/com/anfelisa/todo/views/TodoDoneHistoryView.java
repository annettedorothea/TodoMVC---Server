package com.anfelisa.todo.views;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.data.ToggleAllData;
import com.anfelisa.todo.models.ITodoDoneHistoryModel;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.models.TodoDoneHistoryModel;

public class TodoDoneHistoryView implements ITodoDoneHistoryView {

	private IDaoProvider daoProvider;
	
	public TodoDoneHistoryView(IDaoProvider daoProvider) {
		super();
		this.daoProvider = daoProvider;
	}

	public void toggle(TodoToggleData data, Handle handle) {
		ITodoModel todo = data.getTodoToBeToggled();
		if (todo != null && data.getDone() == true) {
			ITodoDoneHistoryModel todoDoneHistoryModel = new TodoDoneHistoryModel(createId(data.getUuid(), todo.getId()), todo.getDescription(), data.getSystemTime());
			daoProvider.getTodoDoneHistoryDao().insert(handle, todoDoneHistoryModel);
		}
	}
	public void toggleAll(ToggleAllData data, Handle handle) {
		if (data.getDone()) {
			for (ITodoModel todo : data.getTodosToBeToggled()) {
				ITodoDoneHistoryModel todoDoneHistoryModel = new TodoDoneHistoryModel(createId(data.getUuid(), todo.getId()), todo.getDescription(), data.getSystemTime());
				daoProvider.getTodoDoneHistoryDao().insert(handle, todoDoneHistoryModel);
			}
		}
	}

	private String createId(String uuid, String id) {
		return uuid + "--" + id;
	}

}

/*                    S.D.G.                    */
