/********************************************************************************
 * generated by de.acegen 1.2.1
 ********************************************************************************/




package com.anfelisa.todo.models;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("unused")
@JsonDeserialize(as=TodoToggleModel.class)
public interface ITodoToggleModel {

	String getId();
	void setId(String id);
	
	Boolean getDone();
	void setDone(Boolean done);
	
	com.anfelisa.todo.models.ITodoModel getTodoToBeToggled();
	void setTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled);
	
	java.time.LocalDateTime getUpdatedDateTime();
	void setUpdatedDateTime(java.time.LocalDateTime updatedDateTime);
	
	
	ITodoToggleModel deepCopy();
}




/******* S.D.G. *******/



