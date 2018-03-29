package com.anfelisa.ace;

import org.skife.jdbi.v2.Handle;

import com.anfelisa.todo.models.TodoDao;

public class AppUtils {

	private static TodoDao todoDao = new TodoDao();

	public static void truncateAllViews(Handle handle) {
		todoDao.truncate(handle);
	}

}
