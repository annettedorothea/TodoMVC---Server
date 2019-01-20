package com.anfelisa.todo.data;

import com.anfelisa.ace.IDataContainer;
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

	public void overwriteNotReplayableData(IDataContainer dataContainer) {
		/*if (dataContainer != null) {
			try {
				ITodoListData original = (ITodoListData)dataContainer;
				//overwrite values
			} catch (ClassCastException x) {
				LOG.error("cannot cast data to ITodoListData for overwriting not replayable attributes", x);
			}
		}*/
	}

}

/*       S.D.G.       */
