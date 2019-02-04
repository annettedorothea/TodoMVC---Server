package com.anfelisa.todo.data;

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

}

/*       S.D.G.       */
