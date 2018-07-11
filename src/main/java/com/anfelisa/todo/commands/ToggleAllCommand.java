package com.anfelisa.todo.commands;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.ToggleAllData;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.models.TodoDao;

public class ToggleAllCommand extends AbstractToggleAllCommand {

	static final Logger LOG = LoggerFactory.getLogger(ToggleAllCommand.class);

	private TodoDao todoDao = new TodoDao();

	public ToggleAllCommand(ToggleAllData commandData, DatabaseHandle databaseHandle, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super(commandData, databaseHandle, daoProvider, viewProvider);
	}
	
	@Override
	protected void executeCommand() {
		this.commandData.setUpdatedDateTime(this.commandData.getSystemTime());
		List<ITodoModel> todos = todoDao.selectAll(getHandle());
		boolean allAreDone = true;
		this.commandData.setDone(true);
		for (ITodoModel todo : todos) {
			if (todo.getDone() == false) {
				allAreDone = false;
			}
		}
		this.commandData.setDone(!allAreDone);
		this.commandData.setOutcome(success);
	}

}

/* S.D.G. */
