/********************************************************************************
 * generated by de.acegen 1.2.1
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.acegen.IDataContainer;

import com.anfelisa.todo.models.ITodoListModel;

@JsonDeserialize(as=TodoListData.class)
public interface ITodoListData extends ITodoListModel, IDataContainer {
	
	ITodoListData withTodoList(java.util.List<com.anfelisa.todo.models.ITodoModel> todoList);
	
	ITodoListData withCategoryId(String categoryId);
	
	
	ITodoListData deepCopy();
}



/******* S.D.G. *******/



