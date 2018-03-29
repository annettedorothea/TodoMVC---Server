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
public class TodoIdPresentationalData implements ITodoIdPresentationalData {
	
	@NotNull
	private Integer id;
	
	
	public TodoIdPresentationalData(
		@JsonProperty("id") Integer id
	) {
		this.id = id;
		
	}

	@JsonProperty
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TodoIdPresentationalData withId(Integer id) {
		this.id = id;
		return this;
	}
	
}

/*       S.D.G.       */
