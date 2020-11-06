/********************************************************************************
 * generated by de.acegen 1.0.2
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;

import de.acegen.IDataContainer;
import de.acegen.DateTimeToStringConverter;
import de.acegen.StringToDateTimeConverter;

@SuppressWarnings("all")
public class CreateTodoPayload implements ICreateTodoPayload {
	
	private String description;
	
	public CreateTodoPayload() {
	}
	
	public CreateTodoPayload(com.anfelisa.todo.models.ITodoModel data) {
		description = data.getDescription();
	}
	
	@JsonProperty
	public String getDescription() {
		return this.description;
	}
	
}



/******* S.D.G. *******/



