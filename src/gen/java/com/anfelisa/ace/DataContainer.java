package com.anfelisa.ace;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataContainer implements IDataContainer {

	private String uuid;

	private String createdId;
	
	private DateTime systemTime;
	
	public DataContainer(@JsonProperty("uuid") String uuid) {
		super();
		this.uuid = uuid;
	}

	@JsonProperty
	public String getUuid() {
		return uuid;
	}

	@JsonProperty
	public String getCreatedId() {
		return createdId;
	}

	public void setCreatedId(String createdId) {
		this.createdId = createdId;
	}

	@JsonProperty
	public DateTime getSystemTime() {
		return systemTime;
	}

	@JsonProperty
	public void setSystemTime(DateTime systemTime) {
		this.systemTime = systemTime;
	}

}

