package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.IToggleAllModel;

@SuppressWarnings("all")
public class ToggleAllPresentationalData implements IToggleAllPresentationalData {
	
	@NotNull
	private Boolean done = false;
	
	private java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled;
	
	private org.joda.time.DateTime updatedDateTime;
	
	
	public ToggleAllPresentationalData(
		@JsonProperty("done") Boolean done,
		@JsonProperty("todosToBeToggled") java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
	) {
		this.done = done;
		this.todosToBeToggled = todosToBeToggled;
		this.updatedDateTime = updatedDateTime;
		
	}

	@JsonProperty
	public Boolean getDone() {
		return this.done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public ToggleAllPresentationalData withDone(Boolean done) {
		this.done = done;
		return this;
	}
	
	@JsonProperty
	public java.util.List<com.anfelisa.todo.models.ITodoModel> getTodosToBeToggled() {
		return this.todosToBeToggled;
	}
	public void setTodosToBeToggled(java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled) {
		this.todosToBeToggled = todosToBeToggled;
	}
	public ToggleAllPresentationalData withTodosToBeToggled(java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled) {
		this.todosToBeToggled = todosToBeToggled;
		return this;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getUpdatedDateTime() {
		return this.updatedDateTime;
	}
	public void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public ToggleAllPresentationalData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
		return this;
	}
	
}

/*       S.D.G.       */
