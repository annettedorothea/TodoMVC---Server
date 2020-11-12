package de.acegen;

import com.anfelisa.todo.models.CategoryDao;
import com.anfelisa.todo.models.TodoDao;

public class DaoProvider extends AbstractDaoProvider {

	private TodoDao todoDao = new TodoDao();
	private CategoryDao categoryDao = new CategoryDao();

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

	@Override
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

}
