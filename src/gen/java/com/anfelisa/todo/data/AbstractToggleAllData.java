/********************************************************************************
 * generated by de.acegen
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
import com.anfelisa.todo.models.IToggleAllModel;

import de.acegen.AbstractData;
import de.acegen.IDataContainer;
import de.acegen.DateTimeToStringConverter;
import de.acegen.StringToDateTimeConverter;

@SuppressWarnings("unused")
public abstract class AbstractToggleAllData extends AbstractData implements IToggleAllData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleAllData.class);
	
	private Boolean done = false;
	
	private java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled;
	
	private java.time.LocalDateTime updatedDateTime;
	
	private String categoryId;
	

	public AbstractToggleAllData(
		@JsonProperty("done") Boolean done,
		@JsonProperty("todosToBeToggled") java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled,
		@JsonProperty("updatedDateTime") java.time.LocalDateTime updatedDateTime,
		@JsonProperty("categoryId") String categoryId
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.done = done;
		this.todosToBeToggled = todosToBeToggled;
		this.updatedDateTime = updatedDateTime;
		this.categoryId = categoryId;
	}

	public AbstractToggleAllData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public Boolean getDone() {
		return this.done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public IToggleAllData withDone(Boolean done) {
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
	public IToggleAllData withTodosToBeToggled(java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled) {
		this.todosToBeToggled = todosToBeToggled;
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
	public IToggleAllData withUpdatedDateTime(java.time.LocalDateTime updatedDateTime) {
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
	public IToggleAllData withCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	
	
	public IToggleAllData deepCopy() {
		IToggleAllData copy = new ToggleAllData(this.getUuid());
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
		copy.setSystemTime(this.getSystemTime());
		return copy;
	}

}



/******* S.D.G. *******/



