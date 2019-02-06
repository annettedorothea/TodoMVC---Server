package com.anfelisa.ace;

import org.joda.time.DateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractData implements IDataContainer {

	private String uuid;
	
	private String outcome;
	
	private DateTime systemTime;
	
	private Map<String, String> uuidsForTriggeredAction;
	
	public AbstractData( String uuid ) {
		this.uuid = uuid;
		this.uuidsForTriggeredAction = new HashMap<String, String>();
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
	public DateTime getSystemTime() {
		return systemTime;
	}

	@JsonProperty
	public void setSystemTime(DateTime systemTime) {
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
	
	@JsonProperty
	public Map<String, String> getUuidsForTriggeredAction() {
		return uuidsForTriggeredAction;
	}

	@JsonProperty
	public void setUuidsForTriggeredAction(Map<String, String> uuidsForTriggeredAction) {
		this.uuidsForTriggeredAction = uuidsForTriggeredAction;
	}

	public void addUuidForTriggeredAction(String actionName, String uuid) {
		uuidsForTriggeredAction.put(actionName, uuid);
	}

	public String getUuidForTriggeredAction(String actionName) {
		return uuidsForTriggeredAction.get(actionName);
	}

}

/*       S.D.G.       */
