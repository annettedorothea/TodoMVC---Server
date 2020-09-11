/* 
 * Copyright (c) 2020, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * generated with de.acegen 0.9.10
 *
 */




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

	public static com.anfelisa.todo.models.ITodoListModel generateTestData() {
		java.util.Random random = new java.util.Random();
		int n;
		com.anfelisa.todo.models.ITodoListModel testData = new com.anfelisa.todo.models.TodoListModel();
		java.util.List<com.anfelisa.todo.models.ITodoModel> todoListList = new java.util.ArrayList<com.anfelisa.todo.models.ITodoModel>();
		n = random.nextInt(20) + 1;
		for ( int i = 0; i < n; i++ ) {
			todoListList.add(com.anfelisa.todo.data.TodoData.generateTestData());
		}
		testData.setTodoList(todoListList);
		return testData;
	}
	
}



/******* S.D.G. *******/



