package com.anfelisa.todo.models;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("unused")
@JsonDeserialize(as=TodoDoneHistoryModel.class)
public interface ITodoDoneHistoryModel {

	String getId();
	void setId(String id);
	
	String getDescription();
	void setDescription(String description);
	
	org.joda.time.DateTime getDoneDateTime();
	void setDoneDateTime(org.joda.time.DateTime doneDateTime);
	
	
	List<String> equalsPrimitiveTypes(ITodoDoneHistoryModel other);
	
}

/*       S.D.G.       */
