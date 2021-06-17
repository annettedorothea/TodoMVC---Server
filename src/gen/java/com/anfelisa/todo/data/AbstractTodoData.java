/********************************************************************************
 * generated by de.acegen 1.3.0
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import com.anfelisa.todo.models.ITodoModel;

import de.acegen.AbstractData;
import de.acegen.IDataContainer;
import de.acegen.DateTimeToStringConverter;
import de.acegen.StringToDateTimeConverter;

@SuppressWarnings("unused")
public abstract class AbstractTodoData extends AbstractData implements ITodoData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractTodoData.class);
	
	private String id;
	
	private String description;
	
	private Boolean done = false;
	
	private java.time.LocalDateTime createdDateTime;
	
	private java.time.LocalDateTime updatedDateTime;
	
	private String categoryId;
	

	public AbstractTodoData(
		@JsonProperty("id") String id,
		@JsonProperty("description") String description,
		@JsonProperty("done") Boolean done,
		@JsonProperty("createdDateTime") java.time.LocalDateTime createdDateTime,
		@JsonProperty("updatedDateTime") java.time.LocalDateTime updatedDateTime,
		@JsonProperty("categoryId") String categoryId
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
		this.description = description;
		this.done = done;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
		this.categoryId = categoryId;
	}

	public AbstractTodoData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ITodoData withId(String id) {
		this.id = id;
		return this;
	}
	
	@JsonProperty
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ITodoData withDescription(String description) {
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
	public ITodoData withDone(Boolean done) {
		this.done = done;
		return this;
	}
	
	@JsonProperty
	@JsonSerialize(converter = DateTimeToStringConverter.class)
	@JsonDeserialize(converter = StringToDateTimeConverter.class)
	public java.time.LocalDateTime getCreatedDateTime() {
		return this.createdDateTime;
	}
	public void setCreatedDateTime(java.time.LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public ITodoData withCreatedDateTime(java.time.LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
		return this;
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
	public ITodoData withUpdatedDateTime(java.time.LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
		return this;
	}
	
	@JsonProperty
	public String getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public ITodoData withCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	
	public ITodoData deepCopy() {
		ITodoData copy = new TodoData(this.getUuid());
		copy.setId(this.getId());
		copy.setDescription(this.getDescription());
		copy.setDone(this.getDone());
		copy.setCreatedDateTime(this.getCreatedDateTime());
		copy.setUpdatedDateTime(this.getUpdatedDateTime());
		copy.setCategoryId(this.getCategoryId());
		copy.setSystemTime(this.getSystemTime());
		return copy;
	}

}



/******* S.D.G. *******/



