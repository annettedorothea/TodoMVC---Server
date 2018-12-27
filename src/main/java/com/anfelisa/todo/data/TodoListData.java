package com.anfelisa.todo.data;

import com.anfelisa.ace.AbstractData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoListData extends AbstractData implements ITodoListData {
	
	private java.util.List<com.anfelisa.todo.models.ITodoModel> todoList;
	

	public TodoListData(
		@JsonProperty("todoList") java.util.List<com.anfelisa.todo.models.ITodoModel> todoList
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.todoList = todoList;
	}

	public TodoListData( String uuid ) {
		super(uuid);
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
