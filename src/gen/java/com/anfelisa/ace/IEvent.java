package com.anfelisa.ace;

import org.jdbi.v3.core.Handle;

public interface IEvent {

	String getEventName();
	
	IDataContainer getEventData();
	
	void publish(Handle handle, Handle timelineHandle);
	
	void notifyListeners(Handle handle);

}

