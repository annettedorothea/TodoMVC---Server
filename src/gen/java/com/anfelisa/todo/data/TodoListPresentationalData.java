package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoListModel;

@SuppressWarnings("all")
public class TodoListPresentationalData implements ITodoListPresentationalData {
	
	private java.util.List<com.anfelisa.todo.models.ITodoModel> todoList;
	
	
	public TodoListPresentationalData(
		@JsonProperty("todoList") java.util.List<com.anfelisa.todo.models.ITodoModel> todoList
	) {
		this.todoList = todoList;
		
	}

	@JsonProperty
	public java.util.List<com.anfelisa.todo.models.ITodoModel> getTodoList() {
		return this.todoList;
	}
	public void setTodoList(java.util.List<com.anfelisa.todo.models.ITodoModel> todoList) {
		this.todoList = todoList;
	}
	public TodoListPresentationalData withTodoList(java.util.List<com.anfelisa.todo.models.ITodoModel> todoList) {
		this.todoList = todoList;
		return this;
	}
	
}

/*       S.D.G.       */
