package com.anfelisa.todo.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.junit.Test;

import com.anfelisa.ace.AbstractBaseTest;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.todo.TestUtils;
import com.anfelisa.todo.data.GetAllTodosResponse;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.TodoData;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GetAllTodosTest extends AbstractBaseTest {

	private ITodoData secondTodo;
	private ITodoData firstTodo;
	private ITodoData thirdTodo;
	
	@Test
	public void returnsOK() throws JsonProcessingException {
		createTestTodos();
		
		prepare(createTimeline());

		Response response = TestUtils.callGetAllTodos(TestUtils.randomUUID());

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
	}

	@Test
	public void returnsTodosOrderedByCreationDate() throws JsonProcessingException {
		createTestTodos();
		
		prepare(createTimeline());
		
		Response response = TestUtils.callGetAllTodos(TestUtils.randomUUID());
		
		GetAllTodosResponse allTodosResponse = response.readEntity(GetAllTodosResponse.class);
		TestUtils.assertEquals(allTodosResponse.getTodoList().get(0), firstTodo);
		TestUtils.assertEquals(allTodosResponse.getTodoList().get(1), secondTodo);
		TestUtils.assertEquals(allTodosResponse.getTodoList().get(2), thirdTodo);
	}
	
	private void createTestTodos() throws JsonProcessingException {
		firstTodo = new TodoData(TestUtils.randomUUID()).withDescription("xyz").withCreatedDateTime(new DateTime(2019, 1, 24, 13, 10)).withId(TestUtils.randomUUID()); 
		secondTodo = new TodoData(TestUtils.randomUUID()).withDescription("abc").withCreatedDateTime(new DateTime(2019, 1, 24, 13, 20)).withId(TestUtils.randomUUID());
		thirdTodo = new TodoData(TestUtils.randomUUID()).withDescription("def").withCreatedDateTime(new DateTime(2019, 1, 25, 13, 0)).withId(TestUtils.randomUUID());
	}
	
	private List<ITimelineItem> createTimeline() throws JsonProcessingException {
		List<ITimelineItem> timeline = new ArrayList<>();
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(secondTodo));
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(firstTodo));
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(thirdTodo));
		return timeline;
	}

	
}
