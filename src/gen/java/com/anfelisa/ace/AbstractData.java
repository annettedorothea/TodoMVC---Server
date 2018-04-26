package com.anfelisa.ace;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractData implements IDataContainer {

	private String uuid;
	
	private String outcome;
	
	private String[] notifiedListeners;

	private org.joda.time.DateTime systemTime;
	
	public AbstractData(String uuid, String outcome, String[] notifiedListeners, DateTime systemTime) {
		super();
		this.uuid = uuid;
		this.outcome = outcome;
		this.notifiedListeners = notifiedListeners;
		this.systemTime = systemTime;
	}

	public AbstractData( String uuid ) {
		this.uuid = uuid;
	}

	@JsonProperty
	public String getUuid() {
		return this.uuid;
	}

	@JsonProperty
	public org.joda.time.DateTime getSystemTime() {
		return systemTime;
	}

	@JsonProperty
	public void setSystemTime(org.joda.time.DateTime systemTime) {
		this.systemTime = systemTime;
	}

	@JsonProperty
	public String getOutcome() {
		return outcome;
	}

	@JsonProperty
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	@Override
	@JsonProperty
	public String[] getNotifiedListeners() {
		return notifiedListeners;
	}

	@Override
	@JsonProperty
	public void setNotifiedListeners(String[] listeners) {
		this.notifiedListeners = listeners;
	}

}

/*       S.D.G.       */
