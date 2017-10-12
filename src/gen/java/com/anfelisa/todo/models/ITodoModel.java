package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoModel.class)
public interface ITodoModel {

	Integer getId();
	String getDescription();
	Boolean getDone();
	org.joda.time.DateTime getCreatedDateTime();
	org.joda.time.DateTime getUpdatedDateTime();


}

/*       S.D.G.       */
