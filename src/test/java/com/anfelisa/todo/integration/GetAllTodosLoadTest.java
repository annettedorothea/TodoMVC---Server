package com.anfelisa.todo.integration;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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

public class GetAllTodosLoadTest extends BaseScenario {

	@Test
	public void many() throws JsonProcessingException {
		int count = 4000;
		List<ITimelineItem> timeline = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			ITodoData todo = new TodoData(randomUUID()).withDescription("todo " + i)
					.withCreatedDateTime(new DateTime(2019, 1, 24, 13, 0).plusSeconds(i)).withId(randomUUID());
			timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(todo));
		}

		prepare(timeline, DROPWIZARD.getLocalPort());

		long start = System.currentTimeMillis();
		Response response = ActionCalls.callGetAllTodos(randomUUID(), DROPWIZARD.getLocalPort());
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("duration " + duration);

		assertThat(duration<500, is(true));

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
		
		GetAllTodosResponse allTodosResponse = response.readEntity(GetAllTodosResponse.class);
		assertThat(allTodosResponse.getTodoList().size(), is(count));
	}

}
