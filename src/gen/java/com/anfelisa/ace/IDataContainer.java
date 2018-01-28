package com.anfelisa.ace;

import org.joda.time.DateTime;

public interface IDataContainer {

	String getUuid();

	String getOutcome();

	void setOutcome(String outcome);
	
	String getCreatedId();
	
	void setCreatedId(String createdId);
	
	DateTime getSystemTime();
	
	void setSystemTime(DateTime systemTime);

}

