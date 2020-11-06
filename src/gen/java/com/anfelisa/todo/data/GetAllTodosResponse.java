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
public class GetAllTodosResponse implements IGetAllTodosResponse {
	
	private java.util.List<com.anfelisa.todo.models.ITodoModel> todoList;
	
	public GetAllTodosResponse() {
	}
	
	public GetAllTodosResponse(com.anfelisa.todo.models.ITodoListModel data) {
		todoList = data.getTodoList();
	}
	
	@JsonProperty
	public java.util.List<com.anfelisa.todo.models.ITodoModel> getTodoList() {
		return this.todoList;
	}
	
}



/******* S.D.G. *******/



