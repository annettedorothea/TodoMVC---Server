/********************************************************************************
 * generated by de.acegen 1.0.5
 ********************************************************************************/




package com.anfelisa.todo.events;

import de.acegen.Event;
import de.acegen.IDaoProvider;
import de.acegen.ViewProvider;
import de.acegen.CustomAppConfiguration;

import com.anfelisa.todo.data.ICategoryData;

public class CreateCategorySuccessEvent extends Event<ICategoryData> {

	public CreateCategorySuccessEvent(ICategoryData eventData, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super("com.anfelisa.todo.events.CreateCategorySuccessEvent", eventData, daoProvider, viewProvider, appConfiguration);
	}

}



/******* S.D.G. *******/



