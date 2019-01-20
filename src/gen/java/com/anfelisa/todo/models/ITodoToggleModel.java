package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoToggleModel.class)
public interface ITodoToggleModel {

	String getId();
	void setId(String id);
	
	Boolean getDone();
	void setDone(Boolean done);
	
	com.anfelisa.todo.models.ITodoModel getTodoToBeToggled();
	void setTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled);
	
	org.joda.time.DateTime getUpdatedDateTime();
	void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime);
	

}

/*       S.D.G.       */
