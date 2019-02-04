package com.anfelisa.ace;

import org.joda.time.DateTime;

public interface IDataContainer {

	String getUuid();

	String getOutcome();

	void setOutcome(String outcome);

	DateTime getSystemTime();

	void setSystemTime(DateTime systemTime);

	void migrateLegacyData(String json);

}

