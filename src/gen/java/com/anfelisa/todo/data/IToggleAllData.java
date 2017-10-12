package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.IToggleAllModel;

@JsonDeserialize(as=ToggleAllData.class)
public interface IToggleAllData extends IToggleAllModel, IDataContainer {

}

/*       S.D.G.       */
