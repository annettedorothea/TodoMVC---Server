package com.anfelisa.ace;

import org.skife.jdbi.v2.Handle;

import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoHistoryDao;

public class AppUtils {

	private static TodoDao todoDao = new TodoDao();

	private static TodoHistoryDao todoHistoryDao = new TodoHistoryDao();

	public static void truncateAllViews(Handle handle) {
		todoDao.truncate(handle);
		todoHistoryDao.truncate(handle);
	}

}
