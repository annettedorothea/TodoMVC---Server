/********************************************************************************
 * generated by de.acegen 1.0.5
 ********************************************************************************/




package com.anfelisa.todo.commands;

import de.acegen.Command;
import de.acegen.CustomAppConfiguration;
import de.acegen.IDaoProvider;
import de.acegen.ViewProvider;
import de.acegen.PersistenceHandle;

import com.anfelisa.todo.data.ITodoToggleData;

public abstract class AbstractToggleTodoCommand extends Command<ITodoToggleData> {

	public AbstractToggleTodoCommand(ITodoToggleData commandParam, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super("com.anfelisa.todo.commands.ToggleTodoCommand", commandParam, daoProvider, viewProvider, appConfiguration);
	}

	protected void addSuccessOutcome() {
		this.commandData.addOutcome("success");
	}

	@Override
	public void publishEvents(PersistenceHandle handle, PersistenceHandle timelineHandle) {
		if (this.commandData.hasOutcome("success")){
			new com.anfelisa.todo.events.ToggleTodoSuccessEvent(this.commandData, daoProvider, viewProvider, appConfiguration).publish(handle, timelineHandle);
		}
	}
	
}




/******* S.D.G. *******/



