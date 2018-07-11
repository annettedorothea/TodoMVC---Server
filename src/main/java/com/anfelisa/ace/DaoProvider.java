package com.anfelisa.ace;

import org.skife.jdbi.v2.Handle;

import com.anfelisa.todo.models.CustomTodoDao;
import com.anfelisa.todo.models.TodoDao;

public class DaoProvider extends AbstractDaoProvider {
	
	private TodoDao todoDao = new TodoDao();
	private CustomTodoDao customTodoDao = new CustomTodoDao();
	
	@Override
	public TodoDao getTodoDao() {
		return todoDao;
	}

	@Override
	public CustomTodoDao getCustomTodoDao() {
		return customTodoDao;
	}

	@Override
	public void truncateAllViews(Handle handle) {
		todoDao.truncate(handle);
	}
}

