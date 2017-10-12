package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=ToggleAllModel.class)
public interface IToggleAllModel {

	Boolean getDone();
	org.joda.time.DateTime getUpdatedDateTime();


}

/*       S.D.G.       */
