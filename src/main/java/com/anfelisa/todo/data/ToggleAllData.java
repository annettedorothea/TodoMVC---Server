package com.anfelisa.todo.data;

import javax.validation.constraints.NotNull;

import com.anfelisa.ace.AbstractData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToggleAllData extends AbstractData implements IToggleAllData {
	
	@NotNull
	private Boolean done = false;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public ToggleAllData(
		@JsonProperty("done") Boolean done,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.done = done;
		this.updatedDateTime = updatedDateTime;
	}

	public ToggleAllData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public Boolean getDone() {
		return this.done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public ToggleAllData withDone(Boolean done) {
		this.done = done;
		return this;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getUpdatedDateTime() {
		return this.updatedDateTime;
	}
	public void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public ToggleAllData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
		return this;
	}
	

	@Override
	public Object toPresentationalData() {
		return new ToggleAllPresentationalData(
			this.done,
			this.updatedDateTime
		);
	}

}

/*       S.D.G.       */
