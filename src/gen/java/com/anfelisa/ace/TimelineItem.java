package com.anfelisa.ace;

import org.joda.time.DateTime;

public class TimelineItem implements ITimelineItem {

	private Integer id;
	
	private String type;
	
	private String method;
	
	private String name;
	
	private DateTime timestamp;
	
	private String data;
	
	private String uuid;

	
	public TimelineItem(Integer id, String type, String method, String name, DateTime timestamp, String data,
			String uuid) {
		super();
		this.id = id;
		this.type = type;
		this.method = method;
		this.name = name;
		this.timestamp = timestamp;
		this.data = data;
		this.uuid = uuid;
	}

	public Integer getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getMethod() {
		return method;
	}

	public String getName() {
		return name;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public String getData() {
		return data;
	}

	public String getUuid() {
		return uuid;
	}
	
	@Override
	public String toString() {
		return "TimelineItem [id=" + id + ", type=" + type + ", method=" + method + ", name=" + name + ", timestamp="
				+ timestamp + ", uuid=" + uuid + "]";
	}

}

