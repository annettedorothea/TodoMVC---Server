package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class TodoDoneHistoryModel implements ITodoDoneHistoryModel {

	@NotNull
	private String id;
	
	@NotNull
	private String description;
	
	private org.joda.time.DateTime doneDateTime;
	

	public TodoDoneHistoryModel(
		@JsonProperty("id") String id,
		@JsonProperty("description") String description,
		@JsonProperty("doneDateTime") org.joda.time.DateTime doneDateTime
	) {
		this.id = id;
		this.description = description;
		this.doneDateTime = doneDateTime;
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
