package com.anfelisa.todo.actions;

import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.E2E;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.models.ITodoModel;

public class GetAllTodosAction extends AbstractGetAllTodosAction {

	static final Logger LOG = LoggerFactory.getLogger(GetAllTodosAction.class);

	public GetAllTodosAction(Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider, E2E e2e) {
		super(jdbi,appConfiguration, daoProvider, viewProvider, e2e);
	}


	protected final void loadDataForGetRequest(Handle readonlyHandle) {
		List<ITodoModel> todos = daoProvider.getTodoDao().selectAllOrderedByCreatedDate(readonlyHandle);
		this.actionData.setTodoList(todos);
	}


	@Override
	public void initActionData() {
		// TODO Auto-generated method stub
		
	}

}

/*       S.D.G.       */
