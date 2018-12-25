package com.anfelisa.todo.actions;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.models.ITodoModel;

public class GetAllTodosAction extends AbstractGetAllTodosAction {

	static final Logger LOG = LoggerFactory.getLogger(GetAllTodosAction.class);

	public GetAllTodosAction(Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(jdbi,appConfiguration, daoProvider, viewProvider);
	}


	protected final void loadDataForGetRequest() {
		List<ITodoModel> todos = daoProvider.getCustomTodoDao().selectAllOrderedByCreatedDate(getHandle());
		this.actionData.setTodoList(todos);
	}

}

/*       S.D.G.       */
