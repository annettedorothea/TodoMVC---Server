package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.IToggleAllModel;

@JsonDeserialize(as=ToggleAllData.class)
public interface IToggleAllData extends IToggleAllModel, IDataContainer {
	
	IToggleAllData withDone(Boolean done);
	
	IToggleAllData withTodosToBeToggled(java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled);
	
	IToggleAllData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime);
	
	
}

/*       S.D.G.       */
