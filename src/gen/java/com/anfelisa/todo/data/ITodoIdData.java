package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoIdModel;

@JsonDeserialize(as=TodoIdData.class)
public interface ITodoIdData extends ITodoIdModel, IDataContainer {

}

/*       S.D.G.       */