package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TodoDoneHistoryModel implements ITodoDoneHistoryModel {

	@NotNull
	private String id;
	
	@NotNull
	private String description;
	
	private org.joda.time.DateTime doneDateTime;
	

	public TodoDoneHistoryModel() {
	}

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
	
	
	public List<String> equalsPrimitiveTypes(ITodoDoneHistoryModel other) {
		List<String> differingAttributes = new ArrayList<String>();
		if (!(this.getId() == null && other.getId() == null) && !this.getId().equals(other.getId())) {
			differingAttributes.add("id: " + this.getId() + " " + other.getId());
		}
		if (!(this.getDescription() == null && other.getDescription() == null) && !this.getDescription().equals(other.getDescription())) {
			differingAttributes.add("description: " + this.getDescription() + " " + other.getDescription());
		}
		if (!(this.getDoneDateTime() == null && other.getDoneDateTime() == null) && !this.getDoneDateTime().equals(other.getDoneDateTime())) {
			differingAttributes.add("doneDateTime: " + this.getDoneDateTime() + " " + other.getDoneDateTime());
		}
		return differingAttributes;
	}
	
}

/*       S.D.G.       */
