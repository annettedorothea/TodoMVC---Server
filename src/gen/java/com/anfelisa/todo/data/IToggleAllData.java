/********************************************************************************
 * generated by de.acegen 1.5.5
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.acegen.IDataContainer;

import com.anfelisa.todo.models.IToggleAllModel;

@JsonDeserialize(as=ToggleAllData.class)
public interface IToggleAllData extends IToggleAllModel, IDataContainer {
	
	IToggleAllData withDone(Boolean done);
	
	IToggleAllData withTodosToBeToggled(java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled);
	
	IToggleAllData withUpdatedDateTime(java.time.LocalDateTime updatedDateTime);
	
	IToggleAllData withCategoryId(String categoryId);
	
	
	IToggleAllData deepCopy();
}



/******* S.D.G. *******/



