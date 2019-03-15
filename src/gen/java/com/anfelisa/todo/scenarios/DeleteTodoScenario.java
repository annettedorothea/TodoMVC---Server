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
public class DeleteTodoScenario extends BaseScenario {

	private void given() throws Exception {
		List<ITimelineItem> timeline = new ArrayList<>();
		
		com.anfelisa.todo.data.TodoData todo0 = new com.anfelisa.todo.data.TodoData("123");
		todo0.setId("123");
		todo0.setDescription("todo 1");
		todo0.setCreatedDateTime(DateTime.parse("10.10.2019 8:22:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")));
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(todo0));
		
		com.anfelisa.todo.data.TodoData todo1 = new com.anfelisa.todo.data.TodoData("234");
		todo1.setId("234");
		todo1.setDescription("todo 2");
		todo1.setCreatedDateTime(DateTime.parse("10.10.2019 9:01:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")));
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(todo1));
		
		prepare(timeline, DROPWIZARD.getLocalPort());
	}
	
	private Response when() throws Exception {
		setSystemTime(DateTime.parse("10.10.2019 9:04:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")), DROPWIZARD.getLocalPort());
		return ActionCalls.callDeleteTodo(randomUUID(), "123", DROPWIZARD.getLocalPort());
	}
	
	private void then(Response response) throws Exception {
		assertThat(response.getStatus(), is(200));
		
		
		setSystemTime(DateTime.parse("10.10.2019 9:05:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")), DROPWIZARD.getLocalPort());

		Response getAllTodos0 = ActionCalls.callGetAllTodos(randomUUID(), DROPWIZARD.getLocalPort());
		
		com.anfelisa.todo.data.TodoListData expectedGetAllTodos0Data = new com.anfelisa.todo.data.TodoListData("");
		List<com.anfelisa.todo.models.ITodoModel> expectedGetAllTodos0DataTodoList = new ArrayList<com.anfelisa.todo.models.ITodoModel>();
		com.anfelisa.todo.models.ITodoModel expectedGetAllTodos0DataTodoList0 = new com.anfelisa.todo.models.TodoModel();
		expectedGetAllTodos0DataTodoList0.setId("234");
		expectedGetAllTodos0DataTodoList0.setDescription("todo 2");
		expectedGetAllTodos0DataTodoList0.setCreatedDateTime(DateTime.parse("10.10.2019 9:01:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")));
		expectedGetAllTodos0DataTodoList.add(expectedGetAllTodos0DataTodoList0);
		expectedGetAllTodos0Data.setTodoList(expectedGetAllTodos0DataTodoList);
		
		com.anfelisa.todo.data.GetAllTodosResponse expectedGetAllTodos0 = new com.anfelisa.todo.data.GetAllTodosResponse(expectedGetAllTodos0Data);

		com.anfelisa.todo.data.GetAllTodosResponse actualGetAllTodos0 = getAllTodos0.readEntity(com.anfelisa.todo.data.GetAllTodosResponse.class);

		assertThat(actualGetAllTodos0, sameBeanAs(expectedGetAllTodos0));
		
		
	}
	
	@Test
	public void deleteTodo() throws Exception {
		given();
		
		Response response = when();

		then(response);
	}
	
	
}




/******* S.D.G. *******/



