package com.anfelisa.todo.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;

import com.anfelisa.ace.App;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.todo.BaseTest;
import com.anfelisa.todo.data.GetAllTodosResponse;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.views.TodoView;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CreateTodoTest extends BaseTest {

	@Mock
	private TodoView todoViewMock;

	@Test
	public void returnsOK() {
		prepare();
		DateTime systemTime = new DateTime(2019, 1, 23, 15, 0, 0);
		setSystemTime(systemTime);

		String uuid = UUID.randomUUID().toString();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1");
		
		Response response = callCreateTodo(todoData);

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));

	}

	@Test
	public void createsTodo() throws JsonProcessingException {
		prepare();
		DateTime systemTime = new DateTime(2019, 1, 23, 15, 0, 0);
		setSystemTime(systemTime);

		String uuid = randomUUID();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1");
		ITodoData expectedTodoData = new TodoData(uuid).withDescription("todo 1").withId(uuid).withDone(false)
				.withCreatedDateTime(systemTime);

		callCreateTodo(todoData);

		List<ITodoModel> todos = daoProvider.getTodoDao().selectAll(handle);
		assertThat(todos.size(), is(1));

		assertEquals(todos.get(0), expectedTodoData);
	}

	@Test
	public void setsDoneToFalse() throws JsonProcessingException {
		prepare();
		DateTime systemTime = new DateTime(2019, 1, 23, 15, 0, 0);
		setSystemTime(systemTime);
		
		String uuid = randomUUID();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1").withDone(true);
		ITodoData expectedTodoData = new TodoData(uuid).withDescription("todo 1").withId(uuid).withDone(false)
				.withCreatedDateTime(systemTime);
		
		callCreateTodo(todoData);
		
		List<ITodoModel> todos = daoProvider.getTodoDao().selectAll(handle);
		assertThat(todos.size(), is(1));
		
		assertEquals(todos.get(0), expectedTodoData);
	}
	
	@Test
	public void createsTodoApiCall() throws JsonProcessingException {
		prepare();
		DateTime systemTime = new DateTime(2019, 1, 23, 15, 0, 0);
		setSystemTime(systemTime);
		
		String uuid = randomUUID();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1");
		ITodoData expectedTodoData = new TodoData(uuid).withDescription("todo 1").withId(uuid).withDone(false)
				.withCreatedDateTime(systemTime);
		
		callCreateTodo(todoData);
		
		Response response = callGetAllTodos(UUID.randomUUID().toString());
		GetAllTodosResponse allTodosResponse = response.readEntity(GetAllTodosResponse.class);
		assertThat(allTodosResponse.getTodoList().size(), is(1));
		assertEquals(allTodosResponse.getTodoList().get(0), expectedTodoData);
	}
	
	//@Test
	public void createsTodoMocked() throws JsonProcessingException {
		List<ITimelineItem> timeline = new ArrayList<>();
		prepare(timeline);

		App.viewProvider.todoView = todoViewMock;

		String uuid = randomUUID();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1");
		ITodoData expectedTodoData = new TodoData(uuid).withDescription("todo 1").withId(uuid).withDone(false);

		callCreateTodo(todoData);

		verify(todoViewMock, times(1)).create(argThat(new ArgumentMatcher<TodoData>() {
			@Override
			public boolean matches(Object argument) {
				assertEquals((TodoData)argument, expectedTodoData);
				return true;
			}
		}), anyObject());

	}

}
