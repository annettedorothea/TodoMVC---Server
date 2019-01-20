package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TodoDoneHistoryModel.class)
public interface ITodoDoneHistoryModel {

	String getId();
	void setId(String id);
	
	String getDescription();
	void setDescription(String description);
	
	org.joda.time.DateTime getDoneDateTime();
	void setDoneDateTime(org.joda.time.DateTime doneDateTime);
	

}

/*       S.D.G.       */
