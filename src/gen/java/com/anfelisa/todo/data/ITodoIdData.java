/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.acegen.IDataContainer;

import com.anfelisa.todo.models.ITodoIdModel;

@JsonDeserialize(as=TodoIdData.class)
public interface ITodoIdData extends ITodoIdModel, IDataContainer {
	
	ITodoIdData withId(String id);
	
	
	ITodoIdData deepCopy();
}



/******* S.D.G. *******/



