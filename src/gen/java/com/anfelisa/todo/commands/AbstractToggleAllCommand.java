/********************************************************************************
 * generated by de.acegen 1.5.4
 ********************************************************************************/




package com.anfelisa.todo.commands;

import de.acegen.Command;
import de.acegen.CustomAppConfiguration;
import de.acegen.IDaoProvider;
import de.acegen.ViewProvider;
import de.acegen.PersistenceHandle;
import de.acegen.Event;

import com.anfelisa.todo.data.IToggleAllData;

@SuppressWarnings("unused")
public abstract class AbstractToggleAllCommand extends Command<IToggleAllData> {

	public AbstractToggleAllCommand(IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super("com.anfelisa.todo.commands.ToggleAllCommand", daoProvider, viewProvider, appConfiguration);
	}

	protected void addSuccessOutcome(IToggleAllData data) {
		data.addOutcome("success");
	}

	@Override
	public void publishEvents(IToggleAllData data, PersistenceHandle handle, PersistenceHandle timelineHandle) {
		if (data.hasOutcome("success")){
			new Event<IToggleAllData>("com.anfelisa.todo.events.ToggleAllSuccessEvent", daoProvider, viewProvider, appConfiguration).publish(data.deepCopy(), handle, timelineHandle);
		}
	}
	
	@Override
	public void publishAfterCommitEvents(IToggleAllData data, PersistenceHandle handle, PersistenceHandle timelineHandle) {
	}
	
}




/******* S.D.G. *******/



