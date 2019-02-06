package com.anfelisa.todo.actions;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.E2E;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

public class UpdateTodoAction extends AbstractUpdateTodoAction {

	static final Logger LOG = LoggerFactory.getLogger(UpdateTodoAction.class);

	public UpdateTodoAction(Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider, E2E e2e) {
		super(jdbi,appConfiguration, daoProvider, viewProvider, e2e);
	}

	@Override
	public void initActionData() {
		// TODO Auto-generated method stub
		
	}



}

/*       S.D.G.       */
