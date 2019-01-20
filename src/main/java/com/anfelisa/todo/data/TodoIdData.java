package com.anfelisa.todo.data;

import com.anfelisa.ace.IDataContainer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoIdData extends AbstractTodoIdData implements ITodoIdData {
	
	public TodoIdData(
		@JsonProperty("id") String id, 
		@JsonProperty("uuid") String uuid
	) {
		super(
			id,
			uuid
		);
	}

	public TodoIdData( String uuid ) {
		super(uuid);
	}


	public void migrateLegacyData(String json) {
	}

	public void overwriteNotReplayableData(IDataContainer dataContainer) {
		/*if (dataContainer != null) {
			try {
				ITodoIdData original = (ITodoIdData)dataContainer;
				//overwrite values
			} catch (ClassCastException x) {
				LOG.error("cannot cast data to ITodoIdData for overwriting not replayable attributes", x);
			}
		}*/
	}

}

/*       S.D.G.       */
