package com.anfelisa.todo.data;

import com.anfelisa.ace.IDataContainer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClearDoneData extends AbstractClearDoneData implements IClearDoneData {
	
	public ClearDoneData(
		@JsonProperty("uuid") String uuid
	) {
		super(
			uuid
		);
	}



	public void migrateLegacyData(String json) {
	}

	public void overwriteNotReplayableData(IDataContainer dataContainer) {
		/*if (dataContainer != null) {
			try {
				IClearDoneData original = (IClearDoneData)dataContainer;
				//overwrite values
			} catch (ClassCastException x) {
				LOG.error("cannot cast data to IClearDoneData for overwriting not replayable attributes", x);
			}
		}*/
	}

}

/*       S.D.G.       */
