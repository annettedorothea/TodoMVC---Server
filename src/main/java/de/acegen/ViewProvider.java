package de.acegen;

import com.anfelisa.todo.views.CategoryView;
import com.anfelisa.todo.views.TodoView;

public class ViewProvider extends AbstractViewProvider {

	public TodoView todoView;
	public CategoryView categoryView;

	public ViewProvider(IDaoProvider daoProvider) {
		todoView = new TodoView(daoProvider);
		categoryView = new CategoryView(daoProvider);
	}

	public static ViewProvider create(IDaoProvider daoProvider, CustomAppConfiguration configuration) {
		return new ViewProvider(daoProvider);
	}
}
