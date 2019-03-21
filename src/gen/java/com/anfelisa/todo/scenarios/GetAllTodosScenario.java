/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
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
 */




package com.anfelisa.todo.scenarios;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import org.junit.Test;

import com.anfelisa.ace.BaseScenario;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.todo.TestUtils;
import com.anfelisa.todo.ActionCalls;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@SuppressWarnings("unused")
public class GetAllTodosScenario extends BaseScenario {

	private void given() throws Exception {
		List<ITimelineItem> timeline = new ArrayList<>();
		
		com.anfelisa.todo.data.TodoData todo0 = new com.anfelisa.todo.data.TodoData(randomUUID());
		todo0.setId("123");
		todo0.setDescription("xyz");
		todo0.setCreatedDateTime(DateTime.parse("10.10.2019 8:22:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")));
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(todo0));
		
		com.anfelisa.todo.data.TodoData todo1 = new com.anfelisa.todo.data.TodoData(randomUUID());
		todo1.setId("456");
		todo1.setDescription("abc");
		todo1.setCreatedDateTime(DateTime.parse("10.10.2019 9:01:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")));
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(todo1));
		
		prepare(timeline, DROPWIZARD.getLocalPort());
	}
	
	private Response when() throws Exception {
		return ActionCalls.callGetAllTodos(randomUUID(), DROPWIZARD.getLocalPort());
	}
	
	private void then(Response response) throws Exception {
		assertThat(response.getStatus(), is(200));
		
		com.anfelisa.todo.data.TodoListData expectedData = new com.anfelisa.todo.data.TodoListData(randomUUID());
		List<com.anfelisa.todo.models.ITodoModel> expectedDataTodoList = new ArrayList<com.anfelisa.todo.models.ITodoModel>();
		com.anfelisa.todo.models.ITodoModel expectedDataTodoList0 = new com.anfelisa.todo.models.TodoModel();
		expectedDataTodoList0.setId("123");
		expectedDataTodoList0.setDescription("xyz");
		expectedDataTodoList0.setCreatedDateTime(DateTime.parse("10.10.2019 8:22:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")));
		expectedDataTodoList.add(expectedDataTodoList0);
		com.anfelisa.todo.models.ITodoModel expectedDataTodoList1 = new com.anfelisa.todo.models.TodoModel();
		expectedDataTodoList1.setId("456");
		expectedDataTodoList1.setDescription("abc");
		expectedDataTodoList1.setCreatedDateTime(DateTime.parse("10.10.2019 9:01:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")));
		expectedDataTodoList.add(expectedDataTodoList1);
		expectedData.setTodoList(expectedDataTodoList);
		
		com.anfelisa.todo.data.GetAllTodosResponse expected = new com.anfelisa.todo.data.GetAllTodosResponse(expectedData);

		com.anfelisa.todo.data.GetAllTodosResponse actual = response.readEntity(com.anfelisa.todo.data.GetAllTodosResponse.class);

		assertThat(actual, sameBeanAs(expected));
		
	}
	
	@Test
	public void getAllTodos() throws Exception {
		given();
		
		Response response = when();

		then(response);
	}
	
	
}




/******* S.D.G. *******/



