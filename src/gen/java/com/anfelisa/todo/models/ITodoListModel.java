/********************************************************************************
 * generated by de.acegen 1.2.1
 ********************************************************************************/




package com.anfelisa.todo.models;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("unused")
@JsonDeserialize(as=TodoListModel.class)
public interface ITodoListModel {

	java.util.List<com.anfelisa.todo.models.ITodoModel> getTodoList();
	void setTodoList(java.util.List<com.anfelisa.todo.models.ITodoModel> todoList);
	
	String getCategoryId();
	void setCategoryId(String categoryId);
	
	
	ITodoListModel deepCopy();
}




/******* S.D.G. *******/



