package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoToggleModel.class)
public interface ITodoToggleModel {

	Integer getId();
	org.joda.time.DateTime getUpdatedDateTime();


}

/*       S.D.G.       */
