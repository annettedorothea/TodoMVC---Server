package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoModel;

@JsonDeserialize(as=TodoData.class)
public interface ITodoData extends ITodoModel, IDataContainer {
}

/*       S.D.G.       */
