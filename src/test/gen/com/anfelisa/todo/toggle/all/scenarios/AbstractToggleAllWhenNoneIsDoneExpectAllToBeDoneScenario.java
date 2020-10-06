/********************************************************************************
 * generated by de.acegen 0.9.10
 ********************************************************************************/




package com.anfelisa.todo.toggle.all.scenarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acegen.BaseScenario;
import de.acegen.ITimelineItem;
import de.acegen.NonDeterministicDataProvider;

@SuppressWarnings("unused")
public abstract class AbstractToggleAllWhenNoneIsDoneExpectAllToBeDoneScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleAllWhenNoneIsDoneExpectAllToBeDoneScenario.class);
	
	private Map<String, Object> extractedValues = new HashMap<String, Object>();
	
	private void given() throws Exception {
		Response response;
		String uuid;
		long timeBeforeRequest;
		long timeAfterRequest;
		
		if (prerequisite("CreateTodo")) {
			uuid = "" + this.getTestId() + "";
			this.callNonDeterministicDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 16:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
			com.anfelisa.todo.data.CreateTodoPayload payload_0 = objectMapper.readValue("{" +
				"\"description\" : \"todo " + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.CreateTodoPayload.class);
			com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
			"\"description\" : \"todo " + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.TodoData.class);
			timeBeforeRequest = System.currentTimeMillis();
			response = 
			this.httpPost(
				"todos/create", 
			 	payload_0,
				null,
				uuid
			);
			
			timeAfterRequest = System.currentTimeMillis();
			if (response.getStatus() >= 400) {
				String message = "GIVEN CreateTodo fails\n" + response.readEntity(String.class);
				LOG.info("GIVEN: CreateTodo fails due to {} in {} ms", message, (timeAfterRequest-timeBeforeRequest));
				addToMetrics("CreateTodo", (timeAfterRequest-timeBeforeRequest));
				assertFail(message);
			}
			LOG.info("GIVEN: CreateTodo success in {} ms", (timeAfterRequest-timeBeforeRequest));
			addToMetrics("CreateTodo", (timeAfterRequest-timeBeforeRequest));
			com.anfelisa.todo.data.CreateTodoResponse responseEntity_0 = null;
			try {
				responseEntity_0 = response.readEntity(com.anfelisa.todo.data.CreateTodoResponse.class);
				
				Object todoId = this.extractTodoId(responseEntity_0);
				extractedValues.put("todoId", todoId);
				LOG.info("GIVEN: extracted " + todoId.toString()  + " as todoId");
				
				Object createdDateTime = this.extractCreatedDateTime(responseEntity_0);
				extractedValues.put("createdDateTime", createdDateTime);
				LOG.info("GIVEN: extracted " + createdDateTime.toString()  + " as createdDateTime");
				
				Object description = this.extractDescription(responseEntity_0);
				extractedValues.put("description", description);
				LOG.info("GIVEN: extracted " + description.toString()  + " as description");
			} catch (Exception x) {
				LOG.info("GIVEN: failed to extract values from response ", x);
			}
		} else {
			LOG.info("GIVEN: prerequisite for CreateTodo not met");
		}

		if (prerequisite("CreateSecondTodo")) {
			uuid = "" + this.getTestId() + "_2";
			this.callNonDeterministicDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 16:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
			com.anfelisa.todo.data.CreateTodoPayload payload_1 = objectMapper.readValue("{" +
				"\"description\" : \"todo 2 " + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.CreateTodoPayload.class);
			com.anfelisa.todo.data.TodoData data_1 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
			"\"description\" : \"todo 2 " + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.TodoData.class);
			timeBeforeRequest = System.currentTimeMillis();
			response = 
			this.httpPost(
				"todos/create", 
			 	payload_1,
				null,
				uuid
			);
			
			timeAfterRequest = System.currentTimeMillis();
			if (response.getStatus() >= 400) {
				String message = "GIVEN CreateSecondTodo fails\n" + response.readEntity(String.class);
				LOG.info("GIVEN: CreateSecondTodo fails due to {} in {} ms", message, (timeAfterRequest-timeBeforeRequest));
				addToMetrics("CreateTodo", (timeAfterRequest-timeBeforeRequest));
				assertFail(message);
			}
			LOG.info("GIVEN: CreateSecondTodo success in {} ms", (timeAfterRequest-timeBeforeRequest));
			addToMetrics("CreateTodo", (timeAfterRequest-timeBeforeRequest));
		} else {
			LOG.info("GIVEN: prerequisite for CreateSecondTodo not met");
		}

	}
	
	private Response when() throws Exception {
		String uuid = this.randomUUID();
		this.callNonDeterministicDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 17:20", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
		com.anfelisa.todo.data.ToggleAllData data_0 = objectMapper.readValue("{" +
		"\"uuid\" : \"" + uuid + "\" }",
		com.anfelisa.todo.data.ToggleAllData.class);
		long timeBeforeRequest = System.currentTimeMillis();
		Response response = 
		this.httpPut(
			"todos/toggle-all", 
		 	null,
			null,
			uuid
		);
		
		long timeAfterRequest = System.currentTimeMillis();
		LOG.info("WHEN: ToggleAll finished in {} ms", (timeAfterRequest-timeBeforeRequest));
		addToMetrics("ToggleAll", (timeAfterRequest-timeBeforeRequest));
		return response;
	}
	
	private void then(Response response) throws Exception {
		if (response.getStatus() == 500) {
			String message = response.readEntity(String.class);
			assertFail(message);
		}
		if (response.getStatus() != 200) {
			String message = response.readEntity(String.class);
			assertFail(message);
		} else {
			LOG.info("THEN: status 200 passed");
		}
		
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("ToggleAllWhenNoneIsDoneExpectAllToBeDone")) {
			Response response = when();

			then(response);
			
			this.todoIsDone();
			this.secondTodoIsDone();
	
			
			response.close();
		} else {
			LOG.info("WHEN: prerequisite for ToggleAllWhenNoneIsDoneExpectAllToBeDone not met");
		}
	}
	
	
	private void todoIsDone() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.getTestId() + "");
		
		com.anfelisa.todo.models.ITodoModel expected = objectMapper.readValue("{" +
			"\"createdDateTime\" : \"2020-07-07T16:30\"," + 
			"\"description\" : \"todo " + this.getTestId() + "\"," + 
			"\"done\" : true," + 
			"\"id\" : \"" + this.getTestId() + "\"," + 
			"\"updatedDateTime\" : \"2020-07-07T17:20\"} ",
		com.anfelisa.todo.models.TodoModel.class);
		assertThat(actual, expected);
	
		LOG.info("THEN: todoIsDone passed");
	}
	private void secondTodoIsDone() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.getTestId() + "_2");
		
		com.anfelisa.todo.models.ITodoModel expected = objectMapper.readValue("{" +
			"\"createdDateTime\" : \"2020-07-07T16:30\"," + 
			"\"description\" : \"todo 2 " + this.getTestId() + "\"," + 
			"\"done\" : true," + 
			"\"id\" : \"" + this.getTestId() + "_2\"," + 
			"\"updatedDateTime\" : \"2020-07-07T17:20\"} ",
		com.anfelisa.todo.models.TodoModel.class);
		assertThat(actual, expected);
	
		LOG.info("THEN: secondTodoIsDone passed");
	}
		
	@Override
	protected String scenarioName() {
		return "ToggleAllWhenNoneIsDoneExpectAllToBeDone";
	}
	
}



/******* S.D.G. *******/



