/********************************************************************************
 * generated by de.acegen 1.5.5
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.acegen.IDataContainer;

import com.anfelisa.todo.models.ITodoToggleModel;

@JsonDeserialize(as=TodoToggleData.class)
public interface ITodoToggleData extends ITodoToggleModel, IDataContainer {
	
	ITodoToggleData withId(String id);
	
	ITodoToggleData withDone(Boolean done);
	
	ITodoToggleData withTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled);
	
	ITodoToggleData withUpdatedDateTime(java.time.LocalDateTime updatedDateTime);
	
	
	ITodoToggleData deepCopy();
}



/******* S.D.G. *******/



