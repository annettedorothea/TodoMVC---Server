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
	private Handle readonlyHandleMock;
	
	private ITodoModel doneTodo;
	private ITodoModel openTodo;
	
	private DateTime systemTime;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		doneTodo = new TodoModel("1", "done", true, null, null);
		openTodo = new TodoModel("2", "open", false, null, null);
		when(daoProviderMock.getTodoDao()).thenReturn(todoDaoMock);
		when(todoDaoMock.selectById(readonlyHandleMock, doneTodo.getId())).thenReturn(doneTodo);
		when(todoDaoMock.selectById(readonlyHandleMock, openTodo.getId())).thenReturn(openTodo);

		commandData = new TodoToggleData("uuid");
		systemTime = new DateTime();
		commandData.setSystemTime(systemTime); 
		commandData.setId(doneTodo.getId()); 

		command = new ToggleTodoCommand(commandData, daoProviderMock, null, null);
	}

	@Test
	public void setsTodoInCommandData() {
		command.executeCommand(readonlyHandleMock);

		assertThat(commandData.getTodoToBeToggled(), is(doneTodo));
	}
	
	@Test
	public void setsUpdatedDateTime() {
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getUpdatedDateTime(), is(systemTime));
	}
	
	@Test
	public void setsOutcomeSuccess() {
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getOutcome(), is(ToggleTodoCommand.success));
	}
	
	@Test
	public void togglesDone() {
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getDone(), is(false));
	}
	
	@Test
	public void togglesOpen() {
		commandData.setId(openTodo.getId()); 
		
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getDone(), is(true));
	}
	
}
