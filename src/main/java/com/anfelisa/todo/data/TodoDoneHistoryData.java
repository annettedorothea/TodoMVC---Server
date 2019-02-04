package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoDoneHistoryData extends AbstractTodoDoneHistoryData implements ITodoDoneHistoryData {
	
	public TodoDoneHistoryData(
		@JsonProperty("id") String id, 
		@JsonProperty("description") String description, 
		@JsonProperty("doneDateTime") org.joda.time.DateTime doneDateTime, 
		@JsonProperty("uuid") String uuid
	) {
		super(
			id,
			description,
			doneDateTime,
			uuid
		);
	}

	public TodoDoneHistoryData( String uuid ) {
		super(uuid);
	}


	public void migrateLegacyData(String json) {
	}

}

/*       S.D.G.       */
