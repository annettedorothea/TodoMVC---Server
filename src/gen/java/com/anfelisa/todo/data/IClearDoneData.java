package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;


@JsonDeserialize(as=ClearDoneData.class)
public interface IClearDoneData extends  IDataContainer {

}

/*       S.D.G.       */
