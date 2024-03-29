/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package de.acegen;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public abstract class AbstractData implements IDataContainer {

	private String uuid;
	
	private List<String> outcomes;
	
	private LocalDateTime systemTime;
	
	public AbstractData( String uuid ) {
		this.uuid = uuid;
		outcomes = new ArrayList<String>();
	}

	@JsonProperty
	public String getUuid() {
		return this.uuid;
	}

	@JsonProperty
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@JsonProperty
	@JsonSerialize(converter = DateTimeToStringConverter.class)
	@JsonDeserialize(converter = StringToDateTimeConverter.class)
	public LocalDateTime getSystemTime() {
		return systemTime;
	}

	@JsonProperty
	public void setSystemTime(LocalDateTime systemTime) {
		this.systemTime = systemTime;
	}

	@JsonProperty
	public List<String> getOutcomes() {
		return this.outcomes;
	}
	
	public boolean hasOutcome(String outcome) {
		return outcomes.contains(outcome);
	}

	@JsonProperty
	public void addOutcome(String outcome) {
		this.outcomes.add(outcome);
	}
	
}




/******* S.D.G. *******/



