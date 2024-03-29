/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.ArrayList;

import de.acegen.DateTimeToStringConverter;
import de.acegen.StringToDateTimeConverter;

@SuppressWarnings("all")
public class ToggleAllModel implements IToggleAllModel {

	private Boolean done = false;

	private java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled;

	private java.time.LocalDateTime updatedDateTime;

	private String categoryId;


	public ToggleAllModel() {
	}

	public ToggleAllModel(
		@JsonProperty("done") Boolean done,
		@JsonProperty("todosToBeToggled") java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled,
		@JsonProperty("updatedDateTime") java.time.LocalDateTime updatedDateTime,
		@JsonProperty("categoryId") String categoryId
	) {
		this.done = done;
		this.todosToBeToggled = todosToBeToggled;
		this.updatedDateTime = updatedDateTime;
		this.categoryId = categoryId;
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
	@JsonSerialize(converter = DateTimeToStringConverter.class)
	@JsonDeserialize(converter = StringToDateTimeConverter.class)
	public java.time.LocalDateTime getUpdatedDateTime() {
		return this.updatedDateTime;
	}
	public void setUpdatedDateTime(java.time.LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	
	@JsonProperty
	public String getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	

	public IToggleAllModel deepCopy() {
		IToggleAllModel copy = new ToggleAllModel();
		copy.setDone(this.getDone());
		List<com.anfelisa.todo.models.ITodoModel> todosToBeToggledCopy = new ArrayList<com.anfelisa.todo.models.ITodoModel>();
		if (this.getTodosToBeToggled() != null) {
			for(com.anfelisa.todo.models.ITodoModel item: this.getTodosToBeToggled()) {
				todosToBeToggledCopy.add(item.deepCopy());
			}
		}
		copy.setTodosToBeToggled(todosToBeToggledCopy);
		copy.setUpdatedDateTime(this.getUpdatedDateTime());
		copy.setCategoryId(this.getCategoryId());
		return copy;
	}

}



/******* S.D.G. *******/



