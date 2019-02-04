package com.anfelisa.todo.data;

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

}

/*       S.D.G.       */
