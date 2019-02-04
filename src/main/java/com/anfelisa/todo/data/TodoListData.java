package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoListData extends AbstractTodoListData implements ITodoListData {
	
	public TodoListData(
		@JsonProperty("todoList") java.util.List<com.anfelisa.todo.models.ITodoModel> todoList, 
		@JsonProperty("uuid") String uuid
	) {
		super(
			todoList,
			uuid
		);
	}

	public TodoListData( String uuid ) {
		super(uuid);
	}


	public void migrateLegacyData(String json) {
	}

}

/*       S.D.G.       */
