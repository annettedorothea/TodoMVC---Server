package com.anfelisa.todo.models;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("unused")
@JsonDeserialize(as=ToggleAllModel.class)
public interface IToggleAllModel {

	Boolean getDone();
	void setDone(Boolean done);
	
	java.util.List<com.anfelisa.todo.models.ITodoModel> getTodosToBeToggled();
	void setTodosToBeToggled(java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled);
	
	org.joda.time.DateTime getUpdatedDateTime();
	void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime);
	
	
	List<String> equalsPrimitiveTypes(IToggleAllModel other);
	
}

/*       S.D.G.       */
