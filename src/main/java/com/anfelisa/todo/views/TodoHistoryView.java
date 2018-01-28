package com.anfelisa.todo.views;

import java.util.List;
import java.util.function.BiConsumer;

import org.skife.jdbi.v2.Handle;

import com.anfelisa.todo.data.ClearDoneData;
import com.anfelisa.todo.data.ITodoHistoryData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.data.TodoHistoryData;
import com.anfelisa.todo.data.TodoIdData;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.data.ToggleAllData;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoHistoryDao;

@SuppressWarnings("all")
public class TodoHistoryView {

	private static TodoHistoryDao todoHistoryDao = new TodoHistoryDao();
	private static TodoDao todoDao = new TodoDao();
	

	public static BiConsumer<TodoData, Handle> create = (dataContainer, handle) -> {
		ITodoHistoryData todoHistoryData = new TodoHistoryData(dataContainer.getUuid())
				.withChangedTime(dataContainer.getSystemTime()).withAction("CREATE").withDeleted(false)
				.withDescription(dataContainer.getDescription()).withDone(false).withTodoId(dataContainer.getId());
		todoHistoryDao.insert(handle, todoHistoryData);
	};
	public static BiConsumer<TodoToggleData, Handle> toggle = (dataContainer, handle) -> {
		ITodoModel todo = todoDao.selectById(handle, dataContainer.getId());
		ITodoHistoryData todoHistoryData = new TodoHistoryData(dataContainer.getUuid())
				.withChangedTime(dataContainer.getSystemTime()).withAction("TOGGLE").withDeleted(false)
				.withDescription(todo.getDescription()).withDone(todo.getDone()).withTodoId(dataContainer.getId());
		todoHistoryDao.insert(handle, todoHistoryData);
	};
	public static BiConsumer<ToggleAllData, Handle> toggleAll = (dataContainer, handle) -> {
		List<ITodoModel> todos = todoDao.selectAll(handle);
		for (ITodoModel todo : todos) {
			ITodoHistoryData todoHistoryData = new TodoHistoryData(dataContainer.getUuid())
					.withChangedTime(dataContainer.getSystemTime()).withAction("TOGGLE ALL").withDeleted(false)
					.withDescription(todo.getDescription()).withDone(todo.getDone()).withTodoId(todo.getId());
			todoHistoryDao.insert(handle, todoHistoryData);
		}
	};
	public static BiConsumer<TodoIdData, Handle> delete = (dataContainer, handle) -> {
		ITodoModel todo = todoDao.selectById(handle, dataContainer.getId());
		ITodoHistoryData todoHistoryData = new TodoHistoryData(dataContainer.getUuid())
				.withChangedTime(dataContainer.getSystemTime()).withAction("DELETE").withDeleted(true)
				.withDescription(todo.getDescription()).withDone(todo.getDone()).withTodoId(dataContainer.getId());
		todoHistoryDao.insert(handle, todoHistoryData);
	};
	public static BiConsumer<TodoData, Handle> update = (dataContainer, handle) -> {
		ITodoModel todo = todoDao.selectById(handle, dataContainer.getId());
		ITodoHistoryData todoHistoryData = new TodoHistoryData(dataContainer.getUuid())
				.withChangedTime(dataContainer.getSystemTime()).withAction("UPDATE").withDeleted(false)
				.withDescription(dataContainer.getDescription()).withDone(todo.getDone()).withTodoId(dataContainer.getId());
		todoHistoryDao.insert(handle, todoHistoryData);
	};
	public static BiConsumer<ClearDoneData, Handle> clearDone = (dataContainer, handle) -> {
		List<ITodoModel> todos = todoDao.selectAll(handle);
		for (ITodoModel todo : todos) {
			if (todo.getDone()) {
				ITodoHistoryData todoHistoryData = new TodoHistoryData(dataContainer.getUuid())
						.withChangedTime(dataContainer.getSystemTime()).withAction("CLEAR DONE").withDeleted(true)
						.withDescription(todo.getDescription()).withDone(todo.getDone()).withTodoId(todo.getId());
				todoHistoryDao.insert(handle, todoHistoryData);
			}
		}
	};

}

/* S.D.G. */
