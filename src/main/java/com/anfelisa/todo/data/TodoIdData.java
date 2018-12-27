package com.anfelisa.todo.data;

import javax.validation.constraints.NotNull;

import com.anfelisa.ace.AbstractData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoIdData extends AbstractData implements ITodoIdData {
	
	@NotNull
	private String id;
	

	public TodoIdData(
		@JsonProperty("id") String id
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
	}

	public TodoIdData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}

/*       S.D.G.       */
