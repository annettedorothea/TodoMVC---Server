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

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;

import com.anfelisa.ace.App;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.todo.data.GetAllTodosResponse;
import com.anfelisa.todo.data.ITodoData;
import com.anfelisa.todo.data.TodoData;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.views.TodoView;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CreateTodoTest extends TodoBaseTest {

	@Mock
	private TodoView todoViewMock;

	@Test
	public void returnsOK() {
		prepare();

		String uuid = UUID.randomUUID().toString();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1").withId(uuid);
		

		Response response = callCreateTodo(todoData);

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));

	}

	@Test
	public void createsTodo() throws JsonProcessingException {
		prepare();

		String uuid = UUID.randomUUID().toString();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1").withId(uuid);
		ITodoData expectedTodoData = new TodoData(uuid).withDescription("todo 1").withId(uuid).withDone(false);

		callCreateTodo(todoData);

		List<ITodoModel> todos = daoProvider.getTodoDao().selectAll(handle);
		assertThat(todos.size(), is(1));

		assertEquals(todos.get(0), expectedTodoData);
	}

	@Test
	public void createsTodoApiCall() throws JsonProcessingException {
		prepare();
		
		String uuid = UUID.randomUUID().toString();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1").withId(uuid);
		ITodoData expectedTodoData = new TodoData(uuid).withDescription("todo 1").withId(uuid).withDone(false);
		
		callCreateTodo(todoData);
		
		Response response = callGetAll();
		GetAllTodosResponse allTodosResponse = response.readEntity(GetAllTodosResponse.class);
		assertThat(allTodosResponse.getTodoList().size(), is(1));
		assertEquals(allTodosResponse.getTodoList().get(0), expectedTodoData);
	}
	
	@Test
	public void createsTodoMocked() throws JsonProcessingException {
		List<ITimelineItem> timeline = new ArrayList<>();
		prepare(timeline);

		App.viewProvider.todoView = todoViewMock;

		String uuid = UUID.randomUUID().toString();
		ITodoData todoData = new TodoData(uuid).withDescription("todo 1").withId(uuid);
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
