/********************************************************************************
 * generated by de.acegen 1.0.5
 ********************************************************************************/




package com.anfelisa.todo.create.scenarios;

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
public abstract class AbstractCreateTodoScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractCreateTodoScenario.class);
	
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
				String message = "GIVEN CreateCategory fails\n" + response_0.getStatusMessage();
				LOG.error("GIVEN: CreateCategory fails due to {} in {} ms", message, response_0.getDuration());
				assertFail(message);
			}
			LOG.info("GIVEN: CreateCategory success in {} ms", response_0.getDuration());
			addToMetrics("CreateCategory", response_0.getDuration());
		} else {
			LOG.info("GIVEN: prerequisite for CreateCategory not met");
		}

	}
	
	private HttpResponse<Object> when() throws Exception {
		String uuid = "" + this.getTestId() + "";
		this.callNonDeterministicDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 16:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
		com.anfelisa.todo.data.CreateTodoPayload payload_0 = objectMapper.readValue("{" +
			"\"description\" : \"todo " + this.getTestId() + "\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
				com.anfelisa.todo.data.CreateTodoPayload.class);
		com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
		"\"uuid\" : \"" + uuid + "\"," + 
		"\"description\" : \"todo " + this.getTestId() + "\"," + 
		"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
				com.anfelisa.todo.data.TodoData.class);
		HttpResponse<Object> response = 
		this.httpPost(
			"/todos/create", 
		 	payload_0,
			null,
			uuid,
			null
		);
		
		LOG.info("WHEN: CreateTodo finished in {} ms", response.getDuration());
		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			addToMetrics("CreateTodo", response.getDuration());
		}
		return response;
	}
	
	private void then(HttpResponse<Object> response) throws Exception {
		if (response.getStatusCode() == 500) {
			LOG.error("THEN: status " + response.getStatusCode() + " failed: " + response.getStatusMessage());
			assertFail(response.getStatusMessage());
		}
		if (response.getStatusCode() != 200) {
			LOG.error("THEN: status " + response.getStatusCode() + " failed, expected 200: " + response.getStatusMessage());
			assertFail(response.getStatusMessage());
		} else {
			LOG.info("THEN: status 200 passed");
		}
		
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("CreateTodo")) {
			HttpResponse<Object> response = when();

			then(response);
			
			this.todoWasCreated();
	
		} else {
			LOG.info("WHEN: prerequisite for CreateTodo not met");
		}
	}
	
	
	private void todoWasCreated() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.getTestId() + "");
		
		com.anfelisa.todo.models.ITodoModel expected = objectMapper.readValue("{" +
			"\"createdDateTime\" : \"2020-07-07T16:30\"," + 
			"\"description\" : \"todo " + this.getTestId() + "\"," + 
			"\"done\" : false," + 
			"\"id\" : \"" + this.getTestId() + "\"," + 
			"\"updatedDateTime\" : null," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
		com.anfelisa.todo.models.TodoModel.class);
		assertThat(actual, expected);
	
		LOG.info("THEN: todoWasCreated passed");
	}
		
	@Override
	protected String scenarioName() {
		return "CreateTodo";
	}
	
}



/******* S.D.G. *******/



