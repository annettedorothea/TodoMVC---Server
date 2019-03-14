package com.anfelisa.todo.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.junit.Test;
import org.mockito.Mock;

import com.anfelisa.ace.BaseScenario;
import com.anfelisa.todo.ActionCalls;
import com.anfelisa.todo.data.GetAllTodosResponse;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.models.TodoModel;
import com.anfelisa.todo.views.TodoView;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CreateTodoTest extends BaseScenario {

	@Mock
	private TodoView todoViewMock;

	@Test
	public void returnsOK() {
		prepare(DROPWIZARD.getLocalPort());
		DateTime systemTime = new DateTime(2019, 1, 23, 15, 0, 0);
		setSystemTime(systemTime, DROPWIZARD.getLocalPort());

		Response response = ActionCalls.callCreateTodo(randomUUID(), "todo 1", DROPWIZARD.getLocalPort());

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));

	}

	@Test
	public void createsTodo() throws JsonProcessingException {
		prepare(DROPWIZARD.getLocalPort());
		DateTime systemTime = new DateTime(2019, 1, 23, 15, 0, 0);
		setSystemTime(systemTime, DROPWIZARD.getLocalPort());

		String uuid = randomUUID();
		
		TodoModel expectedTodoData = new TodoModel();
		expectedTodoData.setDescription("todo 1");
		expectedTodoData.setId(uuid);
		expectedTodoData.setDone(false);
		expectedTodoData.setCreatedDateTime(systemTime);

		ActionCalls.callCreateTodo(uuid, "todo 1", DROPWIZARD.getLocalPort());

		List<ITodoModel> todos = daoProvider.getTodoDao().selectAll(handle);
		assertThat(todos.size(), is(1));

		List<String> differingAttributs = expectedTodoData.equalsPrimitiveTypes(todos.get(0));
		assertThat(differingAttributs, is(new ArrayList<String>()));
	}

	@Test
	public void setsDoneToFalse() throws JsonProcessingException {
		prepare(DROPWIZARD.getLocalPort());
		DateTime systemTime = new DateTime(2019, 1, 23, 15, 0, 0);
		setSystemTime(systemTime, DROPWIZARD.getLocalPort());
		
		String uuid = randomUUID();
		
		TodoModel expectedTodoData = new TodoModel();
		expectedTodoData.setDescription("todo 1");
		expectedTodoData.setId(uuid);
		expectedTodoData.setDone(false);
		expectedTodoData.setCreatedDateTime(systemTime);
		
		ActionCalls.callCreateTodo(uuid, "todo 1", DROPWIZARD.getLocalPort());
		
		List<ITodoModel> todos = daoProvider.getTodoDao().selectAll(handle);
		assertThat(todos.size(), is(1));
		
		List<String> differingAttributs = expectedTodoData.equalsPrimitiveTypes(todos.get(0));
		assertThat(differingAttributs, is(new ArrayList<String>()));
	}
	
	@Test
	public void createsTodoApiCall() throws JsonProcessingException {
		prepare(DROPWIZARD.getLocalPort());
		DateTime systemTime = new DateTime(2019, 1, 23, 15, 0, 0);
		setSystemTime(systemTime, DROPWIZARD.getLocalPort());
		
		String uuid = randomUUID();

		TodoModel expectedTodoData = new TodoModel();
		expectedTodoData.setDescription("todo 1");
		expectedTodoData.setId(uuid);
		expectedTodoData.setDone(false);
		expectedTodoData.setCreatedDateTime(systemTime);
		
		ActionCalls.callCreateTodo(uuid, "todo 1", DROPWIZARD.getLocalPort());
		
		Response response = ActionCalls.callGetAllTodos(UUID.randomUUID().toString(), DROPWIZARD.getLocalPort());
		GetAllTodosResponse allTodosResponse = response.readEntity(GetAllTodosResponse.class);
		assertThat(allTodosResponse.getTodoList().size(), is(1));
		
		List<String> differingAttributs = expectedTodoData.equalsPrimitiveTypes(allTodosResponse.getTodoList().get(0));
		assertThat(differingAttributs, is(new ArrayList<String>()));
	}
	
}
