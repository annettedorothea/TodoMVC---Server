package com.anfelisa.todo.views;

import java.util.List;
import java.util.function.BiConsumer;

import org.jdbi.v3.core.Handle;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.data.ToggleAllData;
import com.anfelisa.todo.models.ITodoDoneHistoryModel;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.models.TodoDoneHistoryModel;

@SuppressWarnings("all")
public class TodoDoneHistoryView {

	private IDaoProvider daoProvider;
	
	public TodoDoneHistoryView(IDaoProvider daoProvider) {
		super();
		this.daoProvider = daoProvider;
	}

	public BiConsumer<TodoToggleData, Handle> toggle = (dataContainer, handle) -> {
		ITodoModel todo = dataContainer.getTodoToBeToggled();
		if (todo != null && dataContainer.getDone() == true) {
			ITodoDoneHistoryModel todoDoneHistoryModel = new TodoDoneHistoryModel(createId(dataContainer.getUuid(), todo.getId()), todo.getDescription(), dataContainer.getSystemTime());
			daoProvider.getTodoDoneHistoryDao().insert(handle, todoDoneHistoryModel);
		}
	};

	public BiConsumer<ToggleAllData, Handle> toggleAll = (dataContainer, handle) -> {
		if (dataContainer.getDone()) {
			for (ITodoModel todo : dataContainer.getTodosToBeToggled()) {
				ITodoDoneHistoryModel todoDoneHistoryModel = new TodoDoneHistoryModel(createId(dataContainer.getUuid(), todo.getId()), todo.getDescription(), dataContainer.getSystemTime());
				daoProvider.getTodoDoneHistoryDao().insert(handle, todoDoneHistoryModel);
			}
		}
	};
	
	private String createId(String uuid, String id) {
		return uuid + "--" + id;
	}
	
}

/*                    S.D.G.                    */
