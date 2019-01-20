package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=ToggleAllModel.class)
public interface IToggleAllModel {

	Boolean getDone();
	void setDone(Boolean done);
	
	java.util.List<com.anfelisa.todo.models.ITodoModel> getTodosToBeToggled();
	void setTodosToBeToggled(java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled);
	
	org.joda.time.DateTime getUpdatedDateTime();
	void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime);
	

}

/*       S.D.G.       */
