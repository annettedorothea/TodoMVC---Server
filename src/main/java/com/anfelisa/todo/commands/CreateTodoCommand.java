/********************************************************************************
 * generated by de.acegen 1.2.1
 ********************************************************************************/




package com.anfelisa.todo.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.models.ICategoryModel;

import de.acegen.CustomAppConfiguration;
import de.acegen.IDaoProvider;
import de.acegen.PersistenceHandle;
import de.acegen.ViewProvider;

public class CreateTodoCommand extends AbstractCreateTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(CreateTodoCommand.class);

	public CreateTodoCommand(IDaoProvider daoProvider, ViewProvider viewProvider, 
			CustomAppConfiguration appConfiguration) {
		super(daoProvider, viewProvider, appConfiguration);
	}

	@Override
	protected ITodoData executeCommand(ITodoData data, PersistenceHandle readonlyHandle) {
		ICategoryModel categoryModel = daoProvider.getCategoryDao().selectByCategoryId(readonlyHandle, data.getCategoryId());
		if (categoryModel == null) {
			this.throwIllegalArgumentException("category " + data.getCategoryId() + " does not exist.");
		}
		data.setDone(false);
		data.setCreatedDateTime(data.getSystemTime());
		data.setId(data.getUuid());
		this.addSuccessOutcome(data);
		return data;
	}

}




/******* S.D.G. *******/



