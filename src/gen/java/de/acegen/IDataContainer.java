/********************************************************************************
 * generated by de.acegen 1.5.5
 ********************************************************************************/




package de.acegen;

import java.time.LocalDateTime;
import java.util.List;

public interface IDataContainer {

	String getUuid();
	
	void setUuid(String uuid);

	boolean hasOutcome(String outcome);

	List<String> getOutcomes();

	void addOutcome(String outcome);

	LocalDateTime getSystemTime();

	void setSystemTime(LocalDateTime systemTime);

	void migrateLegacyData(String json);

}




/******* S.D.G. *******/



