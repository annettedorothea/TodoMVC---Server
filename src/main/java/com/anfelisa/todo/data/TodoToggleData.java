package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoToggleData extends AbstractTodoToggleData implements ITodoToggleData {
	
	public TodoToggleData(
		@JsonProperty("id") String id, 
		@JsonProperty("done") Boolean done, 
		@JsonProperty("todoToBeToggled") com.anfelisa.todo.models.ITodoModel todoToBeToggled, 
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime, 
		@JsonProperty("uuid") String uuid
	) {
		super(
			id,
			done,
			todoToBeToggled,
			updatedDateTime,
			uuid
		);
	}

	public TodoToggleData( String uuid ) {
		super(uuid);
	}


	public void migrateLegacyData(String json) {
	}

}

/*       S.D.G.       */
