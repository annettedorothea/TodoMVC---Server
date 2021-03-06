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

import com.anfelisa.todo.data.ICategoryData;
import com.anfelisa.todo.data.CategoryData;
import com.anfelisa.todo.commands.CreateCategoryCommand;

@SuppressWarnings("unused")
public abstract class AbstractCreateCategoryAction extends WriteAction<ICategoryData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractCreateCategoryAction.class);
	
	public AbstractCreateCategoryAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.CreateCategoryAction", persistenceConnection, appConfiguration, daoProvider,
						viewProvider);
	}

	@Override
	public ICommand<ICategoryData> getCommand() {
		return new CreateCategoryCommand(daoProvider, viewProvider, this.appConfiguration);
	}
	
	@Override
	protected ICategoryData initActionDataFromNonDeterministicDataProvider(ICategoryData data) {
		LocalDateTime systemTime = NonDeterministicDataProvider.consumeSystemTime(data.getUuid());
		if (systemTime != null) {
			data.setSystemTime(systemTime);
		}
		return data;
	}

	public ICategoryData initActionData(ICategoryData data) {
		return data;
	}

}




/******* S.D.G. *******/



