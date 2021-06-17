/********************************************************************************
 * generated by de.acegen 1.3.0
 ********************************************************************************/




package com.anfelisa.todo.toggle.scenarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acegen.BaseScenario;
import de.acegen.ITimelineItem;
import de.acegen.NonDeterministicDataProvider;
import de.acegen.HttpResponse;

@SuppressWarnings("unused")
public abstract class AbstractToggleTodoTwiceScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleTodoTwiceScenario.class);
	
	private void given() throws Exception {
		String uuid;
		
		if (prerequisite("CreateCategory")) {
			uuid = this.randomUUID();
			com.anfelisa.todo.data.CreateCategoryPayload payload_0 = objectMapper.readValue("{" +
				"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.CreateCategoryPayload.class);
			com.anfelisa.todo.data.CategoryData data_0 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.CategoryData.class);
			HttpResponse<Object> response_0 = 
			this.httpPost(
				"/category/create", 
			 	payload_0,
				null,
				uuid,
				null
			);
			
			if (response_0.getStatusCode() >= 400) {
				String statusMessage = response_0.getStatusMessage() != null ? response_0.getStatusMessage() : "";
				String message = "GIVEN CreateCategory fails\n" + statusMessage;
				LOG.error("GIVEN: CreateCategory fails due to {} in {} ms", message, response_0.getDuration());
				assertFail(message);
			}
			LOG.info("GIVEN: CreateCategory success in {} ms", response_0.getDuration());
			addToMetrics("CreateCategory", response_0.getDuration());
		} else {
			LOG.info("GIVEN: prerequisite for CreateCategory not met");
		}

		if (prerequisite("CreateTodo")) {
			uuid = "" + this.getTestId() + "";
			this.callNonDeterministicDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 16:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
			com.anfelisa.todo.data.CreateTodoPayload payload_1 = objectMapper.readValue("{" +
				"\"description\" : \"todo " + this.getTestId() + "\"," + 
				"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.CreateTodoPayload.class);
			com.anfelisa.todo.data.TodoData data_1 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
			"\"description\" : \"todo " + this.getTestId() + "\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.TodoData.class);
			HttpResponse<Object> response_1 = 
			this.httpPost(
				"/todos/create", 
			 	payload_1,
				null,
				uuid,
				null
			);
			
			if (response_1.getStatusCode() >= 400) {
				String statusMessage = response_1.getStatusMessage() != null ? response_1.getStatusMessage() : "";
				String message = "GIVEN CreateTodo fails\n" + statusMessage;
				LOG.error("GIVEN: CreateTodo fails due to {} in {} ms", message, response_1.getDuration());
				assertFail(message);
			}
			LOG.info("GIVEN: CreateTodo success in {} ms", response_1.getDuration());
			addToMetrics("CreateTodo", response_1.getDuration());
		} else {
			LOG.info("GIVEN: prerequisite for CreateTodo not met");
		}

		if (prerequisite("ToggleTodo")) {
			uuid = this.randomUUID();
			this.callNonDeterministicDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 17:20", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
			com.anfelisa.todo.data.TodoToggleData data_2 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
			"\"id\" : \"" + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.TodoToggleData.class);
			HttpResponse<Object> response_2 = 
			this.httpPut(
				"/todos/toggle?id=" + data_2.getId() + "", 
			 	null,
				null,
				uuid,
				null
			);
			
			if (response_2.getStatusCode() >= 400) {
				String statusMessage = response_2.getStatusMessage() != null ? response_2.getStatusMessage() : "";
				String message = "GIVEN ToggleTodo fails\n" + statusMessage;
				LOG.error("GIVEN: ToggleTodo fails due to {} in {} ms", message, response_2.getDuration());
				assertFail(message);
			}
			LOG.info("GIVEN: ToggleTodo success in {} ms", response_2.getDuration());
			addToMetrics("ToggleTodo", response_2.getDuration());
		} else {
			LOG.info("GIVEN: prerequisite for ToggleTodo not met");
		}

	}
	
	private HttpResponse<Object> when() throws Exception {
		String uuid = this.randomUUID();
		this.callNonDeterministicDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 17:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
		com.anfelisa.todo.data.TodoToggleData data_0 = objectMapper.readValue("{" +
		"\"uuid\" : \"" + uuid + "\"," + 
		"\"id\" : \"" + this.getTestId() + "\"} ",
				com.anfelisa.todo.data.TodoToggleData.class);
		HttpResponse<Object> response = 
		this.httpPut(
			"/todos/toggle?id=" + data_0.getId() + "", 
		 	null,
			null,
			uuid,
			null
		);
		
		LOG.info("WHEN: ToggleTodo finished in {} ms", response.getDuration());
		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			addToMetrics("ToggleTodo", response.getDuration());
		}
		return response;
	}
	
	private void then(HttpResponse<Object> response) throws Exception {
		if (response.getStatusCode() == 500) {
			String statusMessage = response.getStatusMessage() != null ? response.getStatusMessage() : "";
			LOG.error("THEN: status " + response.getStatusCode() + " failed: " + statusMessage);
			assertFail(statusMessage);
		}
		if (response.getStatusCode() != 200) {
			String statusMessage = response.getStatusMessage() != null ? response.getStatusMessage() : "";
			LOG.error("THEN: status " + response.getStatusCode() + " failed, expected 200: " + statusMessage);
			assertFail(statusMessage);
		} else {
			LOG.info("THEN: status 200 passed");
		}
		
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("ToggleTodoTwice")) {
			HttpResponse<Object> response = when();

			then(response);
			
			this.todoWasToggled();
	
		} else {
			LOG.info("WHEN: prerequisite for ToggleTodoTwice not met");
		}
	}
	
	
	private void todoWasToggled() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.getTestId() + "");
		
		com.anfelisa.todo.models.ITodoModel expected = objectMapper.readValue("{" +
			"\"createdDateTime\" : \"2020-07-07T16:30\"," + 
			"\"description\" : \"todo " + this.getTestId() + "\"," + 
			"\"done\" : false," + 
			"\"id\" : \"" + this.getTestId() + "\"," + 
			"\"updatedDateTime\" : \"2020-07-07T17:30\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
		com.anfelisa.todo.models.TodoModel.class);
		assertThat(actual, expected);
	
		LOG.info("THEN: todoWasToggled passed");
	}
		
	@Override
	protected String scenarioName() {
		return "ToggleTodoTwice";
	}
	
}



/******* S.D.G. *******/



