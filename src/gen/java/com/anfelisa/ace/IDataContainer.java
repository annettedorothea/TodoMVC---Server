package com.anfelisa.ace;

import org.joda.time.DateTime;

public interface IDataContainer {

	String getUuid();
	
	void setUuid(String uuid);

	String getOutcome();

	void setOutcome(String outcome);

	DateTime getSystemTime();

	void setSystemTime(DateTime systemTime);

	void migrateLegacyData(String json);

	void addUuidForTriggeredAction(String actionName, String uuid);

	String getUuidForTriggeredAction(String actionName);

}

