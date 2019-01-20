package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoListModel.class)
public interface ITodoListModel {

	java.util.List<com.anfelisa.todo.models.ITodoModel> getTodoList();
	void setTodoList(java.util.List<com.anfelisa.todo.models.ITodoModel> todoList);
	

}

/*       S.D.G.       */
