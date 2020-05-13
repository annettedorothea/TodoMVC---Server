package com.anfelisa.todo.actions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.models.ITodoModel;

import de.acegen.CustomAppConfiguration;
import de.acegen.E2E;
import de.acegen.IDaoProvider;
import de.acegen.PersistenceConnection;
import de.acegen.PersistenceHandle;
import de.acegen.ViewProvider;

public class GetAllTodosAction extends AbstractGetAllTodosAction {

	static final Logger LOG = LoggerFactory.getLogger(GetAllTodosAction.class);

	public GetAllTodosAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, 
			ViewProvider viewProvider, E2E e2e) {
		super(persistenceConnection, appConfiguration, daoProvider, viewProvider, e2e);
	}

	@Override
	protected void loadDataForGetRequest(PersistenceHandle readonlyHandle) {
		List<ITodoModel> todos = daoProvider.getTodoDao().selectAllOrderedByCreatedDate(readonlyHandle);
		this.actionData.setTodoList(todos);
	}
	
	@Override
	public void initActionData() {
		// TODO Auto-generated method stub
		
	}

}

/*       S.D.G.       */
