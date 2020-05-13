package de.acegen;

import com.anfelisa.todo.views.TodoView;

public class ViewProvider extends AbstractViewProvider {

	public TodoView todoView;

	public ViewProvider(IDaoProvider daoProvider) {
		todoView = new TodoView(daoProvider);
	}

	public static ViewProvider create(IDaoProvider daoProvider, CustomAppConfiguration configuration) {
		return new ViewProvider(daoProvider);
	}
}
