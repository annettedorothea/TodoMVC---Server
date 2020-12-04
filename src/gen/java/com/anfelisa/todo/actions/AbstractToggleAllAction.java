/********************************************************************************
 * generated by de.acegen 1.0.5
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

import com.anfelisa.todo.data.IToggleAllData;
import com.anfelisa.todo.data.ToggleAllData;
import com.anfelisa.todo.commands.ToggleAllCommand;

@SuppressWarnings("unused")
public abstract class AbstractToggleAllAction extends WriteAction<IToggleAllData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleAllAction.class);
	
	public AbstractToggleAllAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.ToggleAllAction", persistenceConnection, appConfiguration, daoProvider,
						viewProvider);
	}

	@Override
	public ICommand getCommand() {
		return new ToggleAllCommand(this.actionData, daoProvider, viewProvider, this.appConfiguration);
	}
	
	@Override
	protected void initActionDataFromNonDeterministicDataProvider() {
		LocalDateTime systemTime = NonDeterministicDataProvider.consumeSystemTime(this.actionData.getUuid());
		if (systemTime != null) {
			this.actionData.setSystemTime(systemTime);
		}
	}

}




/******* S.D.G. *******/



