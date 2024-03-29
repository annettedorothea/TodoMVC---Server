/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoItemData extends AbstractTodoItemData implements ITodoItemData {
	
	public TodoItemData(
		@JsonProperty("id") String id, 
		@JsonProperty("description") String description, 
		@JsonProperty("done") Boolean done, 
		@JsonProperty("uuid") String uuid
	) {
		super(
			id,
			description,
			done,
			uuid
		);
	}

	public TodoItemData( String uuid ) {
		super(uuid);
	}

	public void migrateLegacyData(String json) {
	}

	public static com.anfelisa.todo.models.ITodoItemModel generateTestData() {
		java.util.Random random = new java.util.Random();
		com.anfelisa.todo.models.ITodoItemModel testData = new com.anfelisa.todo.models.TodoItemModel();
		testData.setId(randomString(random));
		testData.setDescription(randomString(random));
		testData.setDone(random.nextBoolean());
		return testData;
	}
	
	private static String randomString(java.util.Random random) {
		String chars = "aaaaaaabcdeeeeeeeffffghiiiiiiijkllllllmmmmnnnnnnnooooooooopqrstttuuuuuuuvxyz";
		int n = random.nextInt(20) + 5;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = random.nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		String string  = sb.toString(); 
		return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
	}
	
}



/******* S.D.G. *******/



