/********************************************************************************
 * generated by de.acegen 1.0.2
 ********************************************************************************/




package com.anfelisa.todo.get.scenarios;

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
public abstract class AbstractGetTodoJsonScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractGetTodoJsonScenario.class);
	
	private void given() throws Exception {
		String uuid;
		
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
			HttpResponse<com.anfelisa.todo.data.CreateTodoResponse> response_0 = 
			this.httpPost(
				"/todos/create", 
			 	payload_0,
				null,
				uuid,
				com.anfelisa.todo.data.CreateTodoResponse.class
			);
			
			if (response_0.getStatusCode() >= 400) {
				String message = "GIVEN CreateTodo fails\n" + response_0.getStatusMessage();
				LOG.error("GIVEN: CreateTodo fails due to {} in {} ms", message, response_0.getDuration());
				assertFail(message);
			}
			LOG.info("GIVEN: CreateTodo success in {} ms", response_0.getDuration());
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
			LOG.info("GIVEN: prerequisite for CreateTodo not met");
		}

	}
	
	private HttpResponse<com.anfelisa.todo.data.GetTodoResponse> when() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{\"id\": \"" + this.extractedValues.get("todoId").toString() + "\"}",
				com.anfelisa.todo.data.TodoData.class);
		HttpResponse<com.anfelisa.todo.data.GetTodoResponse> response = 
		this.httpGet(
			"/todo/" + data_0.getId() + "", 
			null,
			uuid,
			com.anfelisa.todo.data.GetTodoResponse.class
		);
		
		LOG.info("WHEN: GetTodo finished in {} ms", response.getDuration());
		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			addToMetrics("GetTodo", response.getDuration());
		}
		return response;
	}
	
	private com.anfelisa.todo.data.GetTodoResponse then(HttpResponse<com.anfelisa.todo.data.GetTodoResponse> response) throws Exception {
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
		
				com.anfelisa.todo.data.GetTodoResponse actual = null;
				if (response.getStatusCode() < 400) {
					try {
						actual = response.getEntity();
						
					} catch (Exception x) {
						LOG.error("THEN: failed to read response", x);
						assertFail(x.getMessage());
					}

					com.anfelisa.todo.data.TodoData expectedData = objectMapper.readValue("{\"createdDateTime\": \"" + this.extractedValues.get("createdDateTime").toString() + "\", \"description\": \"" + this.extractedValues.get("description").toString() + "\", \"done\": false, \"id\": \"" + this.extractedValues.get("todoId").toString() + "\" }",
					com.anfelisa.todo.data.TodoData.class);
					
					com.anfelisa.todo.data.GetTodoResponse expected = new com.anfelisa.todo.data.GetTodoResponse(expectedData);
					
					assertThat(actual, expected);
					
					LOG.info("THEN: response passed");
				}

				return actual;
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("GetTodoJson")) {
			HttpResponse<com.anfelisa.todo.data.GetTodoResponse> response = when();

			com.anfelisa.todo.data.GetTodoResponse actualResponse = then(response);
			
	
		} else {
			LOG.info("WHEN: prerequisite for GetTodoJson not met");
		}
	}
	
	
		
	@Override
	protected String scenarioName() {
		return "GetTodoJson";
	}
	
}



/******* S.D.G. *******/



