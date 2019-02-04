package com.anfelisa.todo.data;

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

}

/*       S.D.G.       */
