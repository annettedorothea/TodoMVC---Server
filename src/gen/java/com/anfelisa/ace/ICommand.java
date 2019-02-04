package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;

public interface ICommand {

	String getCommandName();

	IDataContainer getCommandData();

	void execute(Handle readonlyHandle, Handle timelineHandle);

	void publishEvents(Handle handle, Handle timelineHandle);
}

