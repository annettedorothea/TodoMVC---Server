package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoListModel;

@JsonDeserialize(as=TodoListData.class)
public interface ITodoListData extends ITodoListModel, IDataContainer {

}

/*       S.D.G.       */
