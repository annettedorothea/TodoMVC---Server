package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;

import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoDoneHistoryDao;

public interface IDaoProvider {
	
	void truncateAllViews(Handle handle);
	
	AceDao getAceDao();
	
	void addExceptionToTimeline(String uuid, Throwable x, DatabaseHandle databaseHandle);
	
	void addPreparingEventToTimeline(IEvent event, String uuid);
	
	public void addEventToTimeline(IEvent event);
	
	public void addCommandToTimeline(ICommand command);

	public void addActionToTimeline(IAction command);
	
	TodoDao getTodoDao();

	TodoDoneHistoryDao getTodoDoneHistoryDao();

}

