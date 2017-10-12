package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoToggleModel;

@JsonDeserialize(as=TodoToggleData.class)
public interface ITodoToggleData extends ITodoToggleModel, IDataContainer {

}

/*       S.D.G.       */
