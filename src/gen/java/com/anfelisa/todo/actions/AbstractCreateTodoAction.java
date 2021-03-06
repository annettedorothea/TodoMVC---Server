/********************************************************************************
 * generated by de.acegen 1.2.1
 ********************************************************************************/




package com.anfelisa.todo.actions;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.StringUtils;

import de.acegen.CustomAppConfiguration;
import de.acegen.HttpMethod;
import de.acegen.ICommand;
import de.acegen.IDaoProvider;
import de.acegen.IDataContainer;
import de.acegen.ITimelineItem;
import de.acegen.ViewProvider;
import de.acegen.NonDeterministicDataProvider;
import de.acegen.PersistenceConnection;
import de.acegen.WriteAction;

import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.commands.CreateTodoCommand;

@SuppressWarnings("unused")
public abstract class AbstractCreateTodoAction extends WriteAction<ITodoData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractCreateTodoAction.class);
	
	public AbstractCreateTodoAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.CreateTodoAction", persistenceConnection, appConfiguration, daoProvider,
						viewProvider);
	}

	@Override
	public ICommand<ITodoData> getCommand() {
		return new CreateTodoCommand(daoProvider, viewProvider, this.appConfiguration);
	}
	
	@Override
	protected ITodoData initActionDataFromNonDeterministicDataProvider(ITodoData data) {
		LocalDateTime systemTime = NonDeterministicDataProvider.consumeSystemTime(data.getUuid());
		if (systemTime != null) {
			data.setSystemTime(systemTime);
		}
		return data;
	}

	public ITodoData initActionData(ITodoData data) {
		return data;
	}

}




/******* S.D.G. *******/



