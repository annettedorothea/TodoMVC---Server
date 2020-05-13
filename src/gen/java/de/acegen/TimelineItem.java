/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */




package de.acegen;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TimelineItem implements ITimelineItem {

	private String type;
	
	private String method;
	
	private String name;
	
	private DateTime timestamp;
	
	private String data;
	
	private String uuid;

	
	public TimelineItem(
		@JsonProperty("type") String type, 
		@JsonProperty("method") String method, 
		@JsonProperty("name") String name, 
		@JsonProperty("timestamp") DateTime timestamp, 
		@JsonProperty("data") String data,
		@JsonProperty("uuid") String uuid
	) {
		super();
		this.type = type;
		this.method = method;
		this.name = name;
		this.timestamp = timestamp;
		this.data = data;
		this.uuid = uuid;
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
		return "TimelineItem [type=" + type + ", method=" + method + ", name=" + name + ", timestamp="
				+ timestamp + ", uuid=" + uuid + "]";
	}

}




/******* S.D.G. *******/



