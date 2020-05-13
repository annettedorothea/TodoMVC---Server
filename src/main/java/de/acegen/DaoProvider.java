package de.acegen;

import com.anfelisa.todo.models.TodoDao;

public class DaoProvider extends AbstractDaoProvider {

	private TodoDao todoDao = new TodoDao();

	@Override
	public void truncateAllViews(PersistenceHandle handle) {
		todoDao.truncate(handle);
	}

	@Override
	public TodoDao getTodoDao() {
		return todoDao;
	}

	public static IDaoProvider create() {
		return new DaoProvider();
	}

}
