/********************************************************************************
 * generated by de.acegen 1.3.0
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

import com.anfelisa.todo.data.IClearDoneData;
import com.anfelisa.todo.data.ClearDoneData;
import com.anfelisa.todo.commands.ClearDoneCommand;

@SuppressWarnings("unused")
public abstract class AbstractClearDoneAction extends WriteAction<IClearDoneData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractClearDoneAction.class);
	
	public AbstractClearDoneAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.ClearDoneAction", persistenceConnection, appConfiguration, daoProvider,
						viewProvider);
	}

	@Override
	public ICommand<IClearDoneData> getCommand() {
		return new ClearDoneCommand(daoProvider, viewProvider, this.appConfiguration);
	}
	
	@Override
	protected IClearDoneData initActionDataFromNonDeterministicDataProvider(IClearDoneData data) {
		LocalDateTime systemTime = NonDeterministicDataProvider.consumeSystemTime(data.getUuid());
		if (systemTime != null) {
			data.setSystemTime(systemTime);
		}
		return data;
	}

	public IClearDoneData initActionData(IClearDoneData data) {
		return data;
	}

}




/******* S.D.G. *******/



