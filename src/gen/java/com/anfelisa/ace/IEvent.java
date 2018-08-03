package com.anfelisa.ace;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IEvent {

	String getEventName();
	
	IDataContainer getEventData();
	
	@JsonIgnore
	DatabaseHandle getDatabaseHandle();
	
	void publish();
	
	void initEventData(String json);
	
	void notifyListeners();

}

