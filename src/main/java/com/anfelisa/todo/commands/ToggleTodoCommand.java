package com.anfelisa.todo.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.ITodoToggleData;
import com.anfelisa.todo.models.ITodoModel;

public class ToggleTodoCommand extends AbstractToggleTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(ToggleTodoCommand.class);

	public ToggleTodoCommand(ITodoToggleData commandData, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(commandData, databaseHandle, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setUpdatedDateTime(this.commandData.getSystemTime());
		ITodoModel todo = daoProvider.getTodoDao().selectById(getHandle(), commandData.getId());
		this.commandData.setTodoToBeToggled(todo);
		this.commandData.setDone(!todo.getDone());
		this.commandData.setOutcome(success);
	}

}

/*       S.D.G.       */
