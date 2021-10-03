/********************************************************************************
 * generated by de.acegen 1.5.6
 ********************************************************************************/




package com.anfelisa.todo.commands;

import de.acegen.Command;
import de.acegen.CustomAppConfiguration;
import de.acegen.IDaoProvider;
import de.acegen.ViewProvider;
import de.acegen.PersistenceHandle;
import de.acegen.Event;

import com.anfelisa.todo.data.ICategoryData;

@SuppressWarnings("unused")
public abstract class AbstractCreateCategoryCommand extends Command<ICategoryData> {

	public AbstractCreateCategoryCommand(IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super("com.anfelisa.todo.commands.CreateCategoryCommand", daoProvider, viewProvider, appConfiguration);
	}

	protected void addSuccessOutcome(ICategoryData data) {
		data.addOutcome("success");
	}
	
	@Override
	public void addEventsToTimeline(ICategoryData data, PersistenceHandle timelineHandle) {
		if (appConfiguration.getConfig().writeTimeline()) {
			if (data.hasOutcome("success")){
				daoProvider.getAceDao().addEventToTimeline("com.anfelisa.todo.events.CreateCategorySuccessEvent", data, timelineHandle);
			}
		}
	}
	
	@Override
	public void publishEvents(ICategoryData data, PersistenceHandle handle, PersistenceHandle timelineHandle) {
		if (data.hasOutcome("success")){
			new Event<ICategoryData>("com.anfelisa.todo.events.CreateCategorySuccessEvent", viewProvider).publish(data.deepCopy(), handle, timelineHandle);
		}
	}
	
	@Override
	public void publishAfterCommitEvents(ICategoryData data, PersistenceHandle handle, PersistenceHandle timelineHandle) {
	}
	
}




/******* S.D.G. *******/



