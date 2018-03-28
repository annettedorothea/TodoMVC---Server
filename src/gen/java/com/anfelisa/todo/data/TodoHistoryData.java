package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoHistoryModel;

@SuppressWarnings("all")
public class TodoHistoryData implements ITodoHistoryData {
	
	private String uuid;
	
	private String outcome;
	
	private String createdId;
	
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
	

	private org.joda.time.DateTime systemTime;
	
	public TodoHistoryData(
		@JsonProperty("id") Integer id,
		@JsonProperty("todoId") Integer todoId,
		@JsonProperty("description") String description,
		@JsonProperty("done") Boolean done,
		@JsonProperty("deleted") Boolean deleted,
		@JsonProperty("changedTime") org.joda.time.DateTime changedTime,
		@JsonProperty("action") String action
,		@JsonProperty("uuid") String uuid
	) {
		this.id = id;
		this.todoId = todoId;
		this.description = description;
		this.done = done;
		this.deleted = deleted;
		this.changedTime = changedTime;
		this.action = action;
		this.uuid = uuid;
	}

	public TodoHistoryData( String uuid ) {
		this.uuid = uuid;
	}

	@JsonProperty
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TodoHistoryData withId(Integer id) {
		this.id = id;
		return this;
	}
	
	@JsonProperty
	public Integer getTodoId() {
		return this.todoId;
	}
	public void setTodoId(Integer todoId) {
		this.todoId = todoId;
	}
	public TodoHistoryData withTodoId(Integer todoId) {
		this.todoId = todoId;
		return this;
	}
	
	@JsonProperty
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TodoHistoryData withDescription(String description) {
		this.description = description;
		return this;
	}
	
	@JsonProperty
	public Boolean getDone() {
		return this.done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public TodoHistoryData withDone(Boolean done) {
		this.done = done;
		return this;
	}
	
	@JsonProperty
	public Boolean getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public TodoHistoryData withDeleted(Boolean deleted) {
		this.deleted = deleted;
		return this;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getChangedTime() {
		return this.changedTime;
	}
	public void setChangedTime(org.joda.time.DateTime changedTime) {
		this.changedTime = changedTime;
	}
	public TodoHistoryData withChangedTime(org.joda.time.DateTime changedTime) {
		this.changedTime = changedTime;
		return this;
	}
	
	@JsonProperty
	public String getAction() {
		return this.action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public TodoHistoryData withAction(String action) {
		this.action = action;
		return this;
	}
	

	@JsonProperty
	public String getUuid() {
		return this.uuid;
	}

	@JsonIgnore
	public String getCreatedId() {
		return createdId;
	}

	public void setCreatedId(String createdId) {
		this.createdId = createdId;
	}

	@JsonProperty
	public org.joda.time.DateTime getSystemTime() {
		return systemTime;
	}

	@JsonProperty
	public void setSystemTime(org.joda.time.DateTime systemTime) {
		this.systemTime = systemTime;
	}

	@JsonProperty
	public String getOutcome() {
		return outcome;
	}

	@JsonProperty
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

}

/*       S.D.G.       */
