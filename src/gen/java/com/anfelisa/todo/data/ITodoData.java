package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoModel;

@JsonDeserialize(as=TodoData.class)
public interface ITodoData extends ITodoModel, IDataContainer {
	
	ITodoData withId(String id);
	
	ITodoData withDescription(String description);
	
	ITodoData withDone(Boolean done);
	
	ITodoData withCreatedDateTime(org.joda.time.DateTime createdDateTime);
	
	ITodoData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime);
	
	
}

/*       S.D.G.       */
