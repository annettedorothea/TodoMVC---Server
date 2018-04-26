package com.anfelisa.todo.data;

import javax.validation.constraints.NotNull;

import com.anfelisa.ace.AbstractData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoToggleData extends AbstractData implements ITodoToggleData {
	
	@NotNull
	private String id;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public TodoToggleData(
		@JsonProperty("id") String id,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
		this.updatedDateTime = updatedDateTime;
	}

	public TodoToggleData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TodoToggleData withId(String id) {
		this.id = id;
		return this;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getUpdatedDateTime() {
		return this.updatedDateTime;
	}
	public void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public TodoToggleData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
		return this;
	}
	

	@Override
	public Object toPresentationalData() {
		return new TodoTogglePresentationalData(
			this.id,
			this.updatedDateTime
		);
	}

}

/*       S.D.G.       */
