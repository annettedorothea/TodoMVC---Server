package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class ToggleAllModel implements IToggleAllModel {

	@NotNull
	private Boolean done = false;
	
	private java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public ToggleAllModel(
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
	
	@JsonProperty
	public java.util.List<com.anfelisa.todo.models.ITodoModel> getTodosToBeToggled() {
		return this.todosToBeToggled;
	}
	public void setTodosToBeToggled(java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled) {
		this.todosToBeToggled = todosToBeToggled;
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
