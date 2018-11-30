package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoToggleModel.class)
public interface ITodoToggleModel {

	String getId();
	Boolean getDone();
	com.anfelisa.todo.models.ITodoModel getTodoToBeToggled();
	org.joda.time.DateTime getUpdatedDateTime();

}

/*       S.D.G.       */
