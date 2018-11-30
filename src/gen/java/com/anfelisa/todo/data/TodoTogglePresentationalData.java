package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoToggleModel;

@SuppressWarnings("all")
public class TodoTogglePresentationalData implements ITodoTogglePresentationalData {
	
	@NotNull
	private String id;
	
	@NotNull
	private Boolean done = false;
	
	private com.anfelisa.todo.models.ITodoModel todoToBeToggled;
	
	private org.joda.time.DateTime updatedDateTime;
	
	
	public TodoTogglePresentationalData(
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
	public TodoTogglePresentationalData withId(String id) {
		this.id = id;
		return this;
	}
	
	@JsonProperty
	public Boolean getDone() {
		return this.done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public TodoTogglePresentationalData withDone(Boolean done) {
		this.done = done;
		return this;
	}
	
	@JsonProperty
	public com.anfelisa.todo.models.ITodoModel getTodoToBeToggled() {
		return this.todoToBeToggled;
	}
	public void setTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled) {
		this.todoToBeToggled = todoToBeToggled;
	}
	public TodoTogglePresentationalData withTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled) {
		this.todoToBeToggled = todoToBeToggled;
		return this;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getUpdatedDateTime() {
		return this.updatedDateTime;
	}
	public void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public TodoTogglePresentationalData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
		return this;
	}
	
}

/*       S.D.G.       */
