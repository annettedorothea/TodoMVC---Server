/********************************************************************************
 * generated by de.acegen 1.5.4
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
import de.acegen.SquishyDataProvider;
import de.acegen.PersistenceConnection;
import de.acegen.WriteAction;

import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.commands.UpdateTodoCommand;

@SuppressWarnings("unused")
public abstract class AbstractUpdateTodoAction extends WriteAction<ITodoData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractUpdateTodoAction.class);
	
	public AbstractUpdateTodoAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.UpdateTodoAction", persistenceConnection, appConfiguration, daoProvider,
						viewProvider);
	}

	@Override
	public ICommand<ITodoData> getCommand() {
		return new UpdateTodoCommand(daoProvider, viewProvider, this.appConfiguration);
	}
	
	@Override
	protected ITodoData initActionDataFromSquishyDataProvider(ITodoData data) {
		LocalDateTime systemTime = SquishyDataProvider.consumeSystemTime(data.getUuid());
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



