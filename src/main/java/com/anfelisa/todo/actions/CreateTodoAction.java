package com.anfelisa.todo.actions;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;

public class CreateTodoAction extends AbstractCreateTodoAction {

	static final Logger LOG = LoggerFactory.getLogger(CreateTodoAction.class);

	public CreateTodoAction(Jdbi jdbi, CustomAppConfiguration appConfiguration, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(jdbi,appConfiguration, daoProvider, viewProvider);
	}

	@Override
	public void initActionData() {
		// TODO Auto-generated method stub
		
	}



}

/*       S.D.G.       */
