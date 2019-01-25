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
import com.anfelisa.todo.data.TodoData;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GetAllTodosTest extends TodoBaseTest {

	@Test
	public void returnsOK() throws JsonProcessingException {
		TodoData todo1 = createCreateTodoData("abc", new DateTime(2019, 1, 24, 13, 20));
		TodoData todo2 = createCreateTodoData("xyz", new DateTime(2019, 1, 24, 13, 10));
		TodoData todo3 = createCreateTodoData("def", new DateTime(2019, 1, 24, 13, 30));

		List<ITimelineItem> timeline = new ArrayList<>();
		timeline.add(createCreateTodoSuccessEvent(todo1));
		timeline.add(createCreateTodoSuccessEvent(todo2));
		timeline.add(createCreateTodoSuccessEvent(todo3));

		prepare(timeline);

		Response response = callGetAll();

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
	}

	@Test
	public void returnsTodosOrderedByCreationDate() throws JsonProcessingException {
		TodoData todo1 = createCreateTodoData("abc", new DateTime(2019, 1, 24, 13, 20));
		TodoData todo2 = createCreateTodoData("xyz", new DateTime(2019, 1, 24, 13, 10));
		TodoData todo3 = createCreateTodoData("def", new DateTime(2019, 1, 24, 13, 30));

		List<ITimelineItem> timeline = new ArrayList<>();
		timeline.add(createCreateTodoSuccessEvent(todo1));
		timeline.add(createCreateTodoSuccessEvent(todo2));
		timeline.add(createCreateTodoSuccessEvent(todo3));

		prepare(timeline);

		Response response = callGetAll();

		GetAllTodosResponse allTodosResponse = response.readEntity(GetAllTodosResponse.class);
		assertThat(allTodosResponse.getTodoList().size(), is(3));
		assertEquals(allTodosResponse.getTodoList().get(0), todo2);
		assertEquals(allTodosResponse.getTodoList().get(1), todo1);
		assertEquals(allTodosResponse.getTodoList().get(2), todo3);
	}

}
