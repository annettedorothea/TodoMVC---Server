package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;

import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoDoneHistoryDao;

public interface IDaoProvider {
	
	void truncateAllViews(Handle handle);
	
	AceDao getAceDao();
	
	TodoDao getTodoDao();

	TodoDoneHistoryDao getTodoDoneHistoryDao();

}

