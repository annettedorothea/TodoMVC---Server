package com.anfelisa.todo.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.junit.Test;

import com.anfelisa.ace.BaseScenario;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.todo.ActionCalls;
import com.anfelisa.todo.TestUtils;
import com.anfelisa.todo.data.GetAllTodosResponse;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.TodoData;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GetAllTodosTest extends BaseScenario {

	private ITodoData secondTodo;
	private ITodoData firstTodo;
	private ITodoData thirdTodo;

	@Test
	public void returnsOK() throws JsonProcessingException {
		createTestTodos();

		prepare(createTimeline(), DROPWIZARD.getLocalPort());

		Response response = ActionCalls.callGetAllTodos(randomUUID(), DROPWIZARD.getLocalPort());

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
	}

	@Test
	public void returnsTodosOrderedByCreationDate() throws JsonProcessingException {
		createTestTodos();

		prepare(createTimeline(), DROPWIZARD.getLocalPort());

		Response response = ActionCalls.callGetAllTodos(randomUUID(), DROPWIZARD.getLocalPort());

		GetAllTodosResponse allTodosResponse = response.readEntity(GetAllTodosResponse.class);

		List<String> differingAttributs = firstTodo.equalsPrimitiveTypes(allTodosResponse.getTodoList().get(0));
		assertThat(differingAttributs, is(new ArrayList<String>()));
		differingAttributs = secondTodo.equalsPrimitiveTypes(allTodosResponse.getTodoList().get(1));
		assertThat(differingAttributs, is(new ArrayList<String>()));
		differingAttributs = thirdTodo.equalsPrimitiveTypes(allTodosResponse.getTodoList().get(2));
		assertThat(differingAttributs, is(new ArrayList<String>()));
	}

	private void createTestTodos() throws JsonProcessingException {
		firstTodo = new TodoData(randomUUID()).withDescription("xyz")
				.withCreatedDateTime(new DateTime(2019, 1, 24, 13, 10)).withId(randomUUID());
		secondTodo = new TodoData(randomUUID()).withDescription("abc")
				.withCreatedDateTime(new DateTime(2019, 1, 24, 13, 20)).withId(randomUUID());
		thirdTodo = new TodoData(randomUUID()).withDescription("def")
				.withCreatedDateTime(new DateTime(2019, 1, 25, 13, 0)).withId(randomUUID());
	}

	private List<ITimelineItem> createTimeline() throws JsonProcessingException {
		List<ITimelineItem> timeline = new ArrayList<>();
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(secondTodo));
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(firstTodo));
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(thirdTodo));
		return timeline;
	}

}
