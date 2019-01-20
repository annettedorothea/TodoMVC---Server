package com.anfelisa.todo.data;

import com.anfelisa.ace.IDataContainer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoData extends AbstractTodoData implements ITodoData {
	
	public TodoData(
		@JsonProperty("id") String id, 
		@JsonProperty("description") String description, 
		@JsonProperty("done") Boolean done, 
		@JsonProperty("createdDateTime") org.joda.time.DateTime createdDateTime, 
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime, 
		@JsonProperty("uuid") String uuid
	) {
		super(
			id,
			description,
			done,
			createdDateTime,
			updatedDateTime,
			uuid
		);
	}

	public TodoData( String uuid ) {
		super(uuid);
	}


	public void migrateLegacyData(String json) {
	}

	public void overwriteNotReplayableData(IDataContainer dataContainer) {
		/*if (dataContainer != null) {
			try {
				ITodoData original = (ITodoData)dataContainer;
				//overwrite values
			} catch (ClassCastException x) {
				LOG.error("cannot cast data to ITodoData for overwriting not replayable attributes", x);
			}
		}*/
	}

}

/*       S.D.G.       */
