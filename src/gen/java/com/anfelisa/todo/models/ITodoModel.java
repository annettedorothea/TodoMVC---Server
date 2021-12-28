/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.models;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("unused")
@JsonDeserialize(as=TodoModel.class)
public interface ITodoModel {

	String getId();
	void setId(String id);
	
	String getDescription();
	void setDescription(String description);
	
	Boolean getDone();
	void setDone(Boolean done);
	
	java.time.LocalDateTime getCreatedDateTime();
	void setCreatedDateTime(java.time.LocalDateTime createdDateTime);
	
	java.time.LocalDateTime getUpdatedDateTime();
	void setUpdatedDateTime(java.time.LocalDateTime updatedDateTime);
	
	String getCategoryId();
	void setCategoryId(String categoryId);
	
	
	ITodoModel deepCopy();
}




/******* S.D.G. *******/



