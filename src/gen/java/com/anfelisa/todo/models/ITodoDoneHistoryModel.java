package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoDoneHistoryModel.class)
public interface ITodoDoneHistoryModel {

	String getId();
	String getDescription();
	org.joda.time.DateTime getDoneDateTime();

}

/*       S.D.G.       */
