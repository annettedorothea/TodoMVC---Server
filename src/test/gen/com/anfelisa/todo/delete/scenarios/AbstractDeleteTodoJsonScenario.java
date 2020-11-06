/********************************************************************************
 * generated by de.acegen 1.0.2
 ********************************************************************************/




package com.anfelisa.todo.delete.scenarios;

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
public abstract class AbstractDeleteTodoJsonScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractDeleteTodoJsonScenario.class);
	
	private void given() throws Exception {
		String uuid;
		
		if (prerequisite("CreateRandomTodo")) {
			uuid = this.randomUUID();
			com.anfelisa.todo.data.CreateTodoPayload payload_0 = objectMapper.readValue("{" +
				"\"description\" : \"" + this.randomString() + " " + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.CreateTodoPayload.class);
			com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
			"\"description\" : \"" + this.randomString() + " " + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.TodoData.class);
			HttpResponse<com.anfelisa.todo.data.CreateTodoResponse> response_0 = 
			this.httpPost(
				"/todos/create", 
			 	payload_0,
				null,
				uuid,
				com.anfelisa.todo.data.CreateTodoResponse.class
			);
			
			if (response_0.getStatusCode() >= 400) {
				String message = "GIVEN CreateRandomTodo fails\n" + response_0.getStatusMessage();
				LOG.error("GIVEN: CreateRandomTodo fails due to {} in {} ms", message, response_0.getDuration());
				assertFail(message);
			}
			LOG.info("GIVEN: CreateRandomTodo success in {} ms", response_0.getDuration());
			addToMetrics("CreateTodo", response_0.getDuration());
			com.anfelisa.todo.data.CreateTodoResponse responseEntity_0 = null;
			try {
				
				Object todoId = this.extractTodoId(response_0.getEntity());
				extractedValues.put("todoId", todoId);
				LOG.info("GIVEN: extracted " + todoId.toString()  + " as todoId");
				
				Object createdDateTime = this.extractCreatedDateTime(response_0.getEntity());
				extractedValues.put("createdDateTime", createdDateTime);
				LOG.info("GIVEN: extracted " + createdDateTime.toString()  + " as createdDateTime");
				
				Object description = this.extractDescription(response_0.getEntity());
				extractedValues.put("description", description);
				LOG.info("GIVEN: extracted " + description.toString()  + " as description");
			} catch (Exception x) {
				LOG.error("GIVEN: failed to extract values from response ", x);
			}
		} else {
			LOG.info("GIVEN: prerequisite for CreateRandomTodo not met");
		}

	}
	
	private HttpResponse<Object> when() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.TodoIdData data_0 = objectMapper.readValue("{\"id\": \"" + this.extractedValues.get("todoId").toString() + "\"}",
				com.anfelisa.todo.data.TodoIdData.class);
		HttpResponse<Object> response = 
		this.httpDelete(
			"/todos/delete?id=" + data_0.getId() + "", 
			null,
			uuid,
			null
		);
		
		LOG.info("WHEN: DeleteTodo finished in {} ms", response.getDuration());
		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			addToMetrics("DeleteTodo", response.getDuration());
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
			
		if (prerequisite("DeleteTodoJson")) {
			HttpResponse<Object> response = when();

			then(response);
			
			this.todoWasDeleted();
	
		} else {
			LOG.info("WHEN: prerequisite for DeleteTodoJson not met");
		}
	}
	
	
	private void todoWasDeleted() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.extractedValues.get("todoId").toString() + "");
		
		assertIsNull(actual);
	
		LOG.info("THEN: todoWasDeleted passed");
	}
		
	@Override
	protected String scenarioName() {
		return "DeleteTodoJson";
	}
	
}



/******* S.D.G. *******/



