package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;

import com.anfelisa.todo.models.CustomTodoDao;
import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoDoneHistoryDao;

public interface IDaoProvider {
	
	void truncateAllViews(Handle handle);
	
	AceDao getAceDao();
	
	void addExceptionToTimeline(String uuid, Throwable x, DatabaseHandle databaseHandle);
	
	void addPreparingEventToTimeline(IEvent event, String uuid);
	
	public void addEventToTimeline(IEvent event);
	
	public void addCommandToTimeline(ICommand command);
	
	void addActionToTimeline(IAction action);

	TodoDao getTodoDao();

	CustomTodoDao getCustomTodoDao();
	
	TodoDoneHistoryDao getTodoDoneHistoryDao();

}

