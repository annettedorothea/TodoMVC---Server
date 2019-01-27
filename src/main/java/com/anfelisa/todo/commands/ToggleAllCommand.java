package com.anfelisa.todo.commands;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.IToggleAllData;
import com.anfelisa.todo.models.ITodoModel;

public class ToggleAllCommand extends AbstractToggleAllCommand {

	static final Logger LOG = LoggerFactory.getLogger(ToggleAllCommand.class);

	public ToggleAllCommand(IToggleAllData commandData, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(commandData, databaseHandle, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setUpdatedDateTime(this.commandData.getSystemTime());
		List<ITodoModel> todos = daoProvider.getTodoDao().selectAll(getHandle());
		List<ITodoModel> open = new ArrayList<>();
		boolean allAreDone = true;
		for (ITodoModel todo : todos) {
			if (todo.getDone() == false) {
				allAreDone = false;
				open.add(todo);
			}
		}
		this.commandData.setDone(!allAreDone);
		if (allAreDone) {
			this.commandData.setTodosToBeToggled(todos);
		} else {
			this.commandData.setTodosToBeToggled(open);
		}
		this.commandData.setOutcome(success);
	}
	
}

/* S.D.G. */
