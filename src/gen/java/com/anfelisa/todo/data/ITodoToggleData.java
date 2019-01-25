package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoToggleModel;

@JsonDeserialize(as=TodoToggleData.class)
public interface ITodoToggleData extends ITodoToggleModel, IDataContainer {
	
	ITodoToggleData withId(String id);
	
	ITodoToggleData withDone(Boolean done);
	
	ITodoToggleData withTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled);
	
	ITodoToggleData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime);
	
	
}

/*       S.D.G.       */
