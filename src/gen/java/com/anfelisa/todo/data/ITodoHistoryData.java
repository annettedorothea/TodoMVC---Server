package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoHistoryModel;

@JsonDeserialize(as=TodoHistoryData.class)
public interface ITodoHistoryData extends ITodoHistoryModel, IDataContainer {

}

/*       S.D.G.       */
