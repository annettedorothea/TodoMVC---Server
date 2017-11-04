package com.anfelisa.ace;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ICommand {

	String getCommandName();
	
	IDataContainer getCommandData();
	
	@JsonIgnore
	DatabaseHandle getDatabaseHandle();
	
	void execute();
}

