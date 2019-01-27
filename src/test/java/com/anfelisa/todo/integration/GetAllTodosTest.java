package com.anfelisa.todo.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.junit.Test;

import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.todo.data.GetAllTodosResponse;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.TodoData;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GetAllTodosTest extends TodoBaseTest {

	private ITodoData secondTodo;
	private ITodoData firstTodo;
	private ITodoData thirdTodo;
	
	@Test
	public void returnsOK() throws JsonProcessingException {
		createTestTodos();
		
		prepare(createTimeline());

		Response response = callGetAllTodos(randomUUID());

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
	}

	@Test
	public void returnsTodosOrderedByCreationDate() throws JsonProcessingException {
		createTestTodos();
		
		prepare(createTimeline());
		
		Response response = callGetAllTodos(randomUUID());
		
		GetAllTodosResponse allTodosResponse = response.readEntity(GetAllTodosResponse.class);
		assertEquals(allTodosResponse.getTodoList().get(0), firstTodo);
		assertEquals(allTodosResponse.getTodoList().get(1), secondTodo);
		assertEquals(allTodosResponse.getTodoList().get(2), thirdTodo);
	}
	
	private void createTestTodos() throws JsonProcessingException {
		firstTodo = new TodoData(randomUUID()).withDescription("xyz").withCreatedDateTime(new DateTime(2019, 1, 24, 13, 10)).withId(randomUUID()); 
		secondTodo = new TodoData(randomUUID()).withDescription("abc").withCreatedDateTime(new DateTime(2019, 1, 24, 13, 20)).withId(randomUUID());
		thirdTodo = new TodoData(randomUUID()).withDescription("def").withCreatedDateTime(new DateTime(2019, 1, 25, 13, 0)).withId(randomUUID());
	}
	
	private List<ITimelineItem> createTimeline() throws JsonProcessingException {
		List<ITimelineItem> timeline = new ArrayList<>();
		timeline.add(createCreateTodoSuccessEventTimelineItem(secondTodo));
		timeline.add(createCreateTodoSuccessEventTimelineItem(firstTodo));
		timeline.add(createCreateTodoSuccessEventTimelineItem(thirdTodo));
		return timeline;
	}
	
}
