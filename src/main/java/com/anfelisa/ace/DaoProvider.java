package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;

import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoDoneHistoryDao;

public class DaoProvider extends AbstractDaoProvider {
	
	private TodoDao todoDao = new TodoDao();
	private TodoDoneHistoryDao todoDoneHistoryDao = new TodoDoneHistoryDao();
	
	@Override
	public void truncateAllViews(Handle handle) {
		todoDao.truncate(handle);
		todoDoneHistoryDao.truncate(handle);
	}

	@Override
	public TodoDao getTodoDao() {
		return todoDao;
	}

	@Override
	public TodoDoneHistoryDao getTodoDoneHistoryDao() {
		return todoDoneHistoryDao;
	}

}

