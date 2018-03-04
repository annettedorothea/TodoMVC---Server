package com.anfelisa.ace;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TimelineItem implements ITimelineItem {

	private Integer id;
	
	private String type;
	
	private String method;
	
	private String name;
	
	private DateTime timestamp;
	
	private String data;
	
	private String uuid;

	
	public TimelineItem(
		@JsonProperty("id") Integer id, 
		@JsonProperty("type") String type, 
		@JsonProperty("method") String method, 
		@JsonProperty("name") String name, 
		@JsonProperty("timestamp") DateTime timestamp, 
		@JsonProperty("data") String data,
		@JsonProperty("uuid") String uuid
	) {
		super();
		this.id = id;
		this.type = type;
		this.method = method;
		this.name = name;
		this.timestamp = timestamp;
		this.data = data;
		this.uuid = uuid;
	}

	@JsonProperty
	public Integer getId() {
		return id;
	}

	@JsonProperty
	public String getType() {
		return type;
	}

	@JsonProperty
	public String getMethod() {
		return method;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public DateTime getTimestamp() {
		return timestamp;
	}

	@JsonProperty
	public String getData() {
		return data;
	}

	@JsonProperty
	public String getUuid() {
		return uuid;
	}
	
	@Override
	public String toString() {
		return "TimelineItem [id=" + id + ", type=" + type + ", method=" + method + ", name=" + name + ", timestamp="
				+ timestamp + ", uuid=" + uuid + "]";
	}

}

