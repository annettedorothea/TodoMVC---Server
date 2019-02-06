package com.anfelisa.todo.commands;

import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.CustomAppConfiguration;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.models.ITodoModel;

public class ToggleTodoCommand extends AbstractToggleTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(ToggleTodoCommand.class);

	public ToggleTodoCommand(ITodoToggleData commandData, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super(commandData, daoProvider, viewProvider, appConfiguration);
	}
	
	@Override
	protected void executeCommand(Handle readonlyHandle) {
		this.commandData.setUpdatedDateTime(this.commandData.getSystemTime());
		ITodoModel todo = daoProvider.getTodoDao().selectById(readonlyHandle, commandData.getId());
		this.commandData.setTodoToBeToggled(todo);
		this.commandData.setDone(!todo.getDone());
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
