package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class TodoListModel implements ITodoListModel {


	private java.util.List<com.anfelisa.todo.models.ITodoModel> todoList;
	

	public TodoListModel(
	) {
	}


	@JsonProperty
	public java.util.List<com.anfelisa.todo.models.ITodoModel> getTodoList() {
		return this.todoList;
	}
	public void setTodoList(java.util.List<com.anfelisa.todo.models.ITodoModel> todoList) {
		this.todoList = todoList;
	}
	
		
}

/*       S.D.G.       */