package com.anfelisa.todo.data;

import javax.validation.constraints.NotNull;

import com.anfelisa.ace.AbstractData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoDoneHistoryData extends AbstractData implements ITodoDoneHistoryData {
	
	@NotNull
	private String id;
	
	@NotNull
	private String description;
	
	private org.joda.time.DateTime doneDateTime;
	

	public TodoDoneHistoryData(
		@JsonProperty("id") String id,
		@JsonProperty("description") String description,
		@JsonProperty("doneDateTime") org.joda.time.DateTime doneDateTime
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
		this.description = description;
		this.doneDateTime = doneDateTime;
	}

	public TodoDoneHistoryData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getDoneDateTime() {
		return this.doneDateTime;
	}
	public void setDoneDateTime(org.joda.time.DateTime doneDateTime) {
		this.doneDateTime = doneDateTime;
	}
	
}

/*       S.D.G.       */
