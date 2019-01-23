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
public abstract class AbstractToggleAllData extends AbstractData implements IToggleAllData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleAllData.class);
	
	@NotNull
	private Boolean done = false;
	
	private java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public AbstractToggleAllData(
		@JsonProperty("done") Boolean done,
		@JsonProperty("todosToBeToggled") java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.done = done;
		this.todosToBeToggled = todosToBeToggled;
		this.updatedDateTime = updatedDateTime;
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