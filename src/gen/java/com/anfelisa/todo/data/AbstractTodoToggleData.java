package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import com.anfelisa.todo.models.ITodoToggleModel;

import com.anfelisa.ace.AbstractData;
import com.anfelisa.ace.IDataContainer;

@SuppressWarnings("unused")
public abstract class AbstractTodoToggleData extends AbstractData implements ITodoToggleData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractTodoToggleData.class);
	
	@NotNull
	private String id;
	
	@NotNull
	private Boolean done = false;
	
	private com.anfelisa.todo.models.ITodoModel todoToBeToggled;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public AbstractTodoToggleData(
		@JsonProperty("id") String id,
		@JsonProperty("done") Boolean done,
		@JsonProperty("todoToBeToggled") com.anfelisa.todo.models.ITodoModel todoToBeToggled,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
		this.done = done;
		this.todoToBeToggled = todoToBeToggled;
		this.updatedDateTime = updatedDateTime;
	}

	public AbstractTodoToggleData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ITodoToggleData withId(String id) {
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
	public ITodoToggleData withDone(Boolean done) {
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
	public ITodoToggleData withTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled) {
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
	public ITodoToggleData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
		return this;
	}
	
	
	
	public List<String> equalsPrimitiveTypes(ITodoToggleModel other) {
		List<String> differingAttributes = new ArrayList<String>();
		if (!(this.getId() == null && other.getId() == null) && !this.getId().equals(other.getId())) {
			differingAttributes.add("id: " + this.getId() + " " + other.getId());
		}
		if (!(this.getDone() == null && other.getDone() == null) && !this.getDone().equals(other.getDone())) {
			differingAttributes.add("done: " + this.getDone() + " " + other.getDone());
		}
		if (!(this.getUpdatedDateTime() == null && other.getUpdatedDateTime() == null) && !this.getUpdatedDateTime().equals(other.getUpdatedDateTime())) {
			differingAttributes.add("updatedDateTime: " + this.getUpdatedDateTime() + " " + other.getUpdatedDateTime());
		}
		return differingAttributes;
	}
	
	
}

/*       S.D.G.       */
