package com.anfelisa.todo.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.Handle;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.todo.data.ToggleAllData;
import com.anfelisa.todo.models.ITodoModel;
import com.anfelisa.todo.models.TodoDao;
import com.anfelisa.todo.models.TodoModel;

public class ToggleAllCommandTest {

	private ToggleAllCommand command;
	private ToggleAllData commandData;

	@Mock
	private IDaoProvider daoProviderMock;

	@Mock
	private TodoDao todoDaoMock;

	@Mock
	private Handle readonlyHandleMock;
	
	private List<ITodoModel> allTodos;
	
	private DateTime systemTime;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		allTodos = new ArrayList<ITodoModel>();
		when(daoProviderMock.getTodoDao()).thenReturn(todoDaoMock);
		when(todoDaoMock.selectAll(readonlyHandleMock)).thenReturn(allTodos);

		commandData = new ToggleAllData("uuid");
		systemTime = new DateTime();
		commandData.setSystemTime(systemTime); 
		
		command = new ToggleAllCommand(commandData, daoProviderMock, null);
	}

	@Test
	public void whenAllAreDone_toggleAll() {
		whenAllAreDone();
		
		command.executeCommand(readonlyHandleMock);

		assertThat(commandData.getTodosToBeToggled(), hasSize(allTodos.size()));
		assertThat(commandData.getTodosToBeToggled(), contains(allTodos.get(0), allTodos.get(1), allTodos.get(2)));
	}
	
	@Test
	public void whenAllAreDone_setDoneFalse() {
		whenAllAreDone();
		
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getDone(), is(false));
	}
	
	@Test
	public void whenAllAreDone_setsOutcomeSuccess() {
		whenAllAreDone();
		
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getOutcome(), is(ToggleAllCommand.success));
	}
	
	@Test
	public void whenAllAreDone_setsUpdatedDateTime() {
		whenAllAreDone();
		
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getUpdatedDateTime(), is(systemTime));
	}
	
	@Test
	public void whenNotAllAreDone_toggleOpen() {
		whenNotAllAreDone();
		
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getTodosToBeToggled(), hasSize(1));
		assertThat(commandData.getTodosToBeToggled(), contains(allTodos.get(1)));
	}
	
	@Test
	public void whenNotAllAreDone_setDoneTrue() {
		whenNotAllAreDone();
		
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getDone(), is(true));
	}
	
	@Test
	public void whenNotAllAreDone_setsOutcomeSuccess() {
		whenNotAllAreDone();
		
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getOutcome(), is(ToggleAllCommand.success));
	}
	
	@Test
	public void whenNotAllAreDone_setsUpdatedDateTime() {
		whenNotAllAreDone();
		
		command.executeCommand(readonlyHandleMock);
		
		assertThat(commandData.getUpdatedDateTime(), is(systemTime));
	}
	
	private void whenAllAreDone() {
		allTodos.add(new TodoModel("1", "description1", true, null, null));
		allTodos.add(new TodoModel("2", "description2", true, null, null));
		allTodos.add(new TodoModel("3", "description3", true, null, null));
	}
	private void whenNotAllAreDone() {
		allTodos.add(new TodoModel("1", "description1", true, null, null));
		allTodos.add(new TodoModel("2", "description2", false, null, null));
		allTodos.add(new TodoModel("3", "description3", true, null, null));
	}
}
