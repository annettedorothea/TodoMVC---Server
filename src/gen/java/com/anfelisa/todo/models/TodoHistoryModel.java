package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class TodoHistoryModel implements ITodoHistoryModel {

	@NotNull
	private Integer id;
	
	private Integer todoId;
	
	private String description;
	
	private Boolean done;
	
	@NotNull
	private Boolean deleted;
	
	private org.joda.time.DateTime changedTime;
	
	@NotNull
	private String action;
	

	public TodoHistoryModel(
		@JsonProperty("id") Integer id,
		@JsonProperty("todoId") Integer todoId,
		@JsonProperty("description") String description,
		@JsonProperty("done") Boolean done,
		@JsonProperty("deleted") Boolean deleted,
		@JsonProperty("changedTime") org.joda.time.DateTime changedTime,
		@JsonProperty("action") String action
	) {
		this.id = id;
		this.todoId = todoId;
		this.description = description;
		this.done = done;
		this.deleted = deleted;
		this.changedTime = changedTime;
		this.action = action;
	}

	@JsonProperty
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty
	public Integer getTodoId() {
		return this.todoId;
	}
	public void setTodoId(Integer todoId) {
		this.todoId = todoId;
	}
	
	@JsonProperty
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public Boolean getDone() {
		return this.done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	
	@JsonProperty
	public Boolean getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getChangedTime() {
		return this.changedTime;
	}
	public void setChangedTime(org.joda.time.DateTime changedTime) {
		this.changedTime = changedTime;
	}
	
	@JsonProperty
	public String getAction() {
		return this.action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	

}

/*       S.D.G.       */
