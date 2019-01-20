package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anfelisa.ace.AbstractData;
import com.anfelisa.ace.IDataContainer;

@SuppressWarnings("unused")
public abstract class AbstractTodoData extends AbstractData implements ITodoData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractTodoData.class);
	
	@NotNull
	private String id;
	
	@NotNull
	private String description;
	
	@NotNull
	private Boolean done = false;
	
	private org.joda.time.DateTime createdDateTime;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public AbstractTodoData(
		@JsonProperty("id") String id,
		@JsonProperty("description") String description,
		@JsonProperty("done") Boolean done,
		@JsonProperty("createdDateTime") org.joda.time.DateTime createdDateTime,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
		this.description = description;
		this.done = done;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
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
	public org.joda.time.DateTime getCreatedDateTime() {
		return this.createdDateTime;
	}
	public void setCreatedDateTime(org.joda.time.DateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
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
