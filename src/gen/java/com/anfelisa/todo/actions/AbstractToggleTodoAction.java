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

import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.commands.ToggleTodoCommand;

@SuppressWarnings("unused")
public abstract class AbstractToggleTodoAction extends WriteAction<ITodoToggleData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleTodoAction.class);
	
	public AbstractToggleTodoAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.ToggleTodoAction", persistenceConnection, appConfiguration, daoProvider,
						viewProvider);
	}

	@Override
	public ICommand<ITodoToggleData> getCommand() {
		return new ToggleTodoCommand(daoProvider, viewProvider, this.appConfiguration);
	}
	
	@Override
	protected ITodoToggleData initActionDataFromNonDeterministicDataProvider(ITodoToggleData data) {
		LocalDateTime systemTime = NonDeterministicDataProvider.consumeSystemTime(data.getUuid());
		if (systemTime != null) {
			data.setSystemTime(systemTime);
		}
		return data;
	}

	public ITodoToggleData initActionData(ITodoToggleData data) {
		return data;
	}

}




/******* S.D.G. *******/



