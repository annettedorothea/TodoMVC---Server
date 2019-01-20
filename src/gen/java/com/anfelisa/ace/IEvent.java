package com.anfelisa.ace;

public interface IEvent {

	String getEventName();
	
	IDataContainer getEventData();
	
	DatabaseHandle getDatabaseHandle();
	
	void publish();
	
	void notifyListeners();

}

