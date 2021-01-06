/********************************************************************************
 * generated by de.acegen 1.1.0
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.acegen.IDataContainer;

import com.anfelisa.todo.models.ITodoModel;

@JsonDeserialize(as=TodoData.class)
public interface ITodoData extends ITodoModel, IDataContainer {
	
	ITodoData withId(String id);
	
	ITodoData withDescription(String description);
	
	ITodoData withDone(Boolean done);
	
	ITodoData withCreatedDateTime(java.time.LocalDateTime createdDateTime);
	
	ITodoData withUpdatedDateTime(java.time.LocalDateTime updatedDateTime);
	
	ITodoData withCategoryId(String categoryId);
	
	
}



/******* S.D.G. *******/



