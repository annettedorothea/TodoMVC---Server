package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoIdModel;

@SuppressWarnings("all")
public class TodoIdData implements ITodoIdData {
	
	private String uuid;
	
	private String createdId;
	
	@NotNull
	private Integer id;
	

	
	private org.joda.time.DateTime systemTime;
	
	public TodoIdData(
		@JsonProperty("id") Integer id
,		@JsonProperty("uuid") String uuid
	) {
		this.id = id;
		this.uuid = uuid;
	}

	public TodoIdData( String uuid ) {
		this.uuid = uuid;
	}

	@JsonProperty
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TodoIdData withId(Integer id) {
		this.id = id;
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

}

/*       S.D.G.       */
