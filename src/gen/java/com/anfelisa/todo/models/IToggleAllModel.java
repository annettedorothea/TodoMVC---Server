package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=ToggleAllModel.class)
public interface IToggleAllModel {

	Boolean getDone();
	java.util.List<com.anfelisa.todo.models.ITodoModel> getTodosToBeToggled();
	org.joda.time.DateTime getUpdatedDateTime();

}

/*       S.D.G.       */
