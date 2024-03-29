/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.actions;

import java.time.LocalDateTime;

import de.acegen.CustomAppConfiguration;
import de.acegen.ICommand;
import de.acegen.IDaoProvider;
import de.acegen.ViewProvider;
import de.acegen.SquishyDataProvider;
import de.acegen.PersistenceConnection;
import de.acegen.WriteAction;

import com.anfelisa.todo.data.IToggleAllData;
import com.anfelisa.todo.commands.ToggleAllCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractToggleAllAction extends WriteAction<IToggleAllData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleAllAction.class);

	public AbstractToggleAllAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.ToggleAllAction", persistenceConnection, appConfiguration, daoProvider, viewProvider);
	}

	@Override
	public ICommand<IToggleAllData> getCommand() {
		return new ToggleAllCommand(daoProvider, viewProvider, this.appConfiguration);
	}
	
	@Override
	protected IToggleAllData initActionDataFromSquishyDataProvider(IToggleAllData data) {
		LocalDateTime systemTime = SquishyDataProvider.consumeSystemTime(data.getUuid());
		if (systemTime != null) {
			data.setSystemTime(systemTime);
		}
		return data;
	}

	public IToggleAllData initActionData(IToggleAllData data) {
		return data;
	}

}




/******* S.D.G. *******/



