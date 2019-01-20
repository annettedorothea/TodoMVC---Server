package com.anfelisa.todo.data;

import com.anfelisa.ace.IDataContainer;
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

	public void overwriteNotReplayableData(IDataContainer dataContainer) {
		/*if (dataContainer != null) {
			try {
				ITodoDoneHistoryData original = (ITodoDoneHistoryData)dataContainer;
				//overwrite values
			} catch (ClassCastException x) {
				LOG.error("cannot cast data to ITodoDoneHistoryData for overwriting not replayable attributes", x);
			}
		}*/
	}

}

/*       S.D.G.       */
