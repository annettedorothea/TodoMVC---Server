package com.anfelisa.todo.data;

import com.anfelisa.ace.IDataContainer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToggleAllData extends AbstractToggleAllData implements IToggleAllData {
	
	public ToggleAllData(
		@JsonProperty("done") Boolean done, 
		@JsonProperty("todosToBeToggled") java.util.List<com.anfelisa.todo.models.ITodoModel> todosToBeToggled, 
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime, 
		@JsonProperty("uuid") String uuid
	) {
		super(
			done,
			todosToBeToggled,
			updatedDateTime,
			uuid
		);
	}

	public ToggleAllData( String uuid ) {
		super(uuid);
	}


	public void migrateLegacyData(String json) {
	}

	public void overwriteNotReplayableData(IDataContainer dataContainer) {
		/*if (dataContainer != null) {
			try {
				IToggleAllData original = (IToggleAllData)dataContainer;
				//overwrite values
			} catch (ClassCastException x) {
				LOG.error("cannot cast data to IToggleAllData for overwriting not replayable attributes", x);
			}
		}*/
	}

}

/*       S.D.G.       */
