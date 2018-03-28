package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoIdModel.class)
public interface ITodoIdModel {

	Integer getId();

}

/*       S.D.G.       */
