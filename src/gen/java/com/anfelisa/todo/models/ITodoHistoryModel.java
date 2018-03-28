package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoHistoryModel.class)
public interface ITodoHistoryModel {

	Integer getId();
	Integer getTodoId();
	String getDescription();
	Boolean getDone();
	Boolean getDeleted();
	org.joda.time.DateTime getChangedTime();
	String getAction();

}

/*       S.D.G.       */
