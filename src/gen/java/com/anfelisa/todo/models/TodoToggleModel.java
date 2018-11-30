package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class TodoToggleModel implements ITodoToggleModel {

	@NotNull
	private String id;
	
	@NotNull
	private Boolean done = false;
	
	private com.anfelisa.todo.models.ITodoModel todoToBeToggled;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public TodoToggleModel(
		@JsonProperty("id") String id,
		@JsonProperty("done") Boolean done,
		@JsonProperty("todoToBeToggled") com.anfelisa.todo.models.ITodoModel todoToBeToggled,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
	) {
		this.id = id;
		this.done = done;
		this.todoToBeToggled = todoToBeToggled;
		this.updatedDateTime = updatedDateTime;
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty
	public Boolean getDone() {
		return this.done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	
	@JsonProperty
	public com.anfelisa.todo.models.ITodoModel getTodoToBeToggled() {
		return this.todoToBeToggled;
	}
	public void setTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled) {
		this.todoToBeToggled = todoToBeToggled;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getUpdatedDateTime() {
		return this.updatedDateTime;
	}
	public void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	

}

/*       S.D.G.       */
