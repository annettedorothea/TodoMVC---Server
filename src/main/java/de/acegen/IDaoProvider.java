package de.acegen;

import com.anfelisa.todo.models.TodoDao;

public interface IDaoProvider {
	
	void truncateAllViews(PersistenceHandle handle);
	
	AceDao getAceDao();
	
	TodoDao getTodoDao();

}

