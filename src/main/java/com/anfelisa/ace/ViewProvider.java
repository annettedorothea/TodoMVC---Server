package com.anfelisa.ace;

import com.anfelisa.todo.views.TodoDoneHistoryView;
import com.anfelisa.todo.views.TodoView;

public class ViewProvider extends AbstractViewProvider {

	public TodoView todoView;
	public TodoDoneHistoryView todoDoneHistoryView;

	public ViewProvider(IDaoProvider daoProvider) {
		todoView = new TodoView(daoProvider);
		todoDoneHistoryView = new TodoDoneHistoryView(daoProvider);
	}
}
