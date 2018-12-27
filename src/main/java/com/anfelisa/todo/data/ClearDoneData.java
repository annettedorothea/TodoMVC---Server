package com.anfelisa.todo.data;

import com.anfelisa.ace.AbstractData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClearDoneData extends AbstractData implements IClearDoneData {
	

	public ClearDoneData(
		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
	}


}

/*       S.D.G.       */
