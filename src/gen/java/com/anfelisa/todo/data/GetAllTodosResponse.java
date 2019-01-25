package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;

import com.anfelisa.ace.IDataContainer;

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

/*       S.D.G.       */
