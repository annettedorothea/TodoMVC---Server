package com.anfelisa.todo.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.jdbi.v3.core.Handle;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.anfelisa.ace.DatabaseHandle;
import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.todo.data.TodoToggleData;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoModel;

public class ToggleTodoCommandTest {
	
	private ToggleTodoCommand command;
	private TodoToggleData commandData;

	@Mock
	private IDaoProvider daoProviderMock;

	@Mock
	private TodoDao todoDaoMock;

	@Mock
	private Handle handleMock;
	
	private ITodoModel doneTodo;
	private ITodoModel openTodo;
	
	private DateTime systemTime;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		doneTodo = new TodoModel("1", "done", true, null, null);
		openTodo = new TodoModel("2", "open", false, null, null);
		when(daoProviderMock.getTodoDao()).thenReturn(todoDaoMock);
		when(todoDaoMock.selectById(handleMock, doneTodo.getId())).thenReturn(doneTodo);
		when(todoDaoMock.selectById(handleMock, openTodo.getId())).thenReturn(openTodo);

		commandData = new TodoToggleData("uuid");
		systemTime = new DateTime();
		commandData.setSystemTime(systemTime); 
		commandData.setId(doneTodo.getId()); 

		command = new ToggleTodoCommand(commandData, new DatabaseHandle(handleMock, null), daoProviderMock, null);
	}

	@Test
	public void setsTodoInCommandData() {
		command.executeCommand();

		assertThat(commandData.getTodoToBeToggled(), is(doneTodo));
	}
	
	@Test
	public void setsUpdatedDateTime() {
		command.executeCommand();
		
		assertThat(commandData.getUpdatedDateTime(), is(systemTime));
	}
	
	@Test
	public void setsOutcomeSuccess() {
		command.executeCommand();
		
		assertThat(commandData.getOutcome(), is(ToggleTodoCommand.success));
	}
	
	@Test
	public void togglesDone() {
		command.executeCommand();
		
		assertThat(commandData.getDone(), is(false));
	}
	
	@Test
	public void togglesOpen() {
		commandData.setId(openTodo.getId()); 
		
		command.executeCommand();
		
		assertThat(commandData.getDone(), is(true));
	}
	
}