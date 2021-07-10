/********************************************************************************
 * generated by de.acegen 1.3.0
 ********************************************************************************/




package com.anfelisa.todo.actions;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.StringUtils;

import de.acegen.CustomAppConfiguration;
import de.acegen.IDaoProvider;
import de.acegen.IDataContainer;
import de.acegen.ViewProvider;
import de.acegen.PersistenceConnection;
import de.acegen.PersistenceHandle;
import de.acegen.ReadAction;
import de.acegen.ITimelineItem;
import de.acegen.SquishyDataProvider;

import com.anfelisa.todo.data.ITodoListData;
import com.anfelisa.todo.data.TodoListData;

@SuppressWarnings("unused")
public abstract class AbstractGetAllTodosAction extends ReadAction<ITodoListData> {

	static final Logger LOG = LoggerFactory.getLogger(AbstractGetAllTodosAction.class);
	
	public AbstractGetAllTodosAction(PersistenceConnection persistenceConnection, CustomAppConfiguration appConfiguration, 
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		super("com.anfelisa.todo.actions.GetAllTodosAction", persistenceConnection, appConfiguration, daoProvider, viewProvider);
	}

	protected abstract ITodoListData loadDataForGetRequest(ITodoListData data, PersistenceHandle readonlyHandle);

	@Override
	protected ITodoListData initActionDataFromSquishyDataProvider(ITodoListData data) {
		LocalDateTime systemTime = SquishyDataProvider.consumeSystemTime(data.getUuid());
		if (systemTime != null) {
			data.setSystemTime(systemTime);
		}
		return data;
	}

	public ITodoListData initActionData(ITodoListData data) {
		return data;
	}

}



/******* S.D.G. *******/



