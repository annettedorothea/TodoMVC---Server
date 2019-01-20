package com.anfelisa.ace;

public interface ICommand {

	String getCommandName();

	IDataContainer getCommandData();

	DatabaseHandle getDatabaseHandle();

	void execute();

	void publishEvents();
}

