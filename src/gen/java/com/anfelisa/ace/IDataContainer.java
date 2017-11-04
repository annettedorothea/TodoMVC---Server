package com.anfelisa.ace;

import org.joda.time.DateTime;

public interface IDataContainer {

	String getUuid();
	
	String getCreatedId();
	
	void setCreatedId(String createdId);
	
	DateTime getSystemTime();
	
	void setSystemTime(DateTime systemTime);
	
}
