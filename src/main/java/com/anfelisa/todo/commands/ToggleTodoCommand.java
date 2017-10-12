package com.anfelisa.todo.commands;

import com.anfelisa.ace.DatabaseHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.todo.data.TodoToggleData;

public class ToggleTodoCommand extends AbstractToggleTodoCommand {

	static final Logger LOG = LoggerFactory.getLogger(ToggleTodoCommand.class);

	public ToggleTodoCommand(TodoToggleData commandParam, DatabaseHandle databaseHandle) {
		super(commandParam, databaseHandle);
	}

	@Override
	protected void executeCommand() {
		this.commandData.setUpdatedDateTime(this.commandData.getSystemTime());
		this.outcome = success;
	}

}

/*       S.D.G.       */
