/********************************************************************************
 * generated by de.acegen 1.2.1
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
public class TodoIdModel implements ITodoIdModel {

	private String id;


	public TodoIdModel() {
	}

	public TodoIdModel(
		@JsonProperty("id") String id
	) {
		this.id = id;
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

	public ITodoIdModel deepCopy() {
		ITodoIdModel copy = new TodoIdModel();
		copy.setId(this.getId());
		return copy;
	}

}



/******* S.D.G. *******/



