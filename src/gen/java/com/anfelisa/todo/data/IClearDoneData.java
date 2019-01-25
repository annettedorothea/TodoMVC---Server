package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.IClearDoneModel;

@JsonDeserialize(as=ClearDoneData.class)
public interface IClearDoneData extends IClearDoneModel, IDataContainer {
	
	
}

/*       S.D.G.       */
