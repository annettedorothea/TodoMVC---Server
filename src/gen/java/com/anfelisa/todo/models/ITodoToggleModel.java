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
	
	org.joda.time.DateTime getUpdatedDateTime();
	void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime);
	
	
	List<String> equalsPrimitiveTypes(ITodoToggleModel other);
	
}

/*       S.D.G.       */
