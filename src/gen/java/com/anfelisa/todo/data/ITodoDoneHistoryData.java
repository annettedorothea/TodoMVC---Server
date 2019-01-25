package com.anfelisa.todo.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.anfelisa.ace.IDataContainer;

import com.anfelisa.todo.models.ITodoDoneHistoryModel;

@JsonDeserialize(as=TodoDoneHistoryData.class)
public interface ITodoDoneHistoryData extends ITodoDoneHistoryModel, IDataContainer {
	
	ITodoDoneHistoryData withId(String id);
	
	ITodoDoneHistoryData withDescription(String description);
	
	ITodoDoneHistoryData withDoneDateTime(org.joda.time.DateTime doneDateTime);
	
	
}

/*       S.D.G.       */
