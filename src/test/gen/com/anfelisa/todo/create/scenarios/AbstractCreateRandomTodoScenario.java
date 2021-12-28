/********************************************************************************
 * generated by de.acegen
 ********************************************************************************/




package com.anfelisa.todo.create.scenarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acegen.BaseScenario;
import de.acegen.ITimelineItem;
import de.acegen.SquishyDataProvider;
import de.acegen.HttpResponse;

@SuppressWarnings("unused")
public abstract class AbstractCreateRandomTodoScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractCreateRandomTodoScenario.class);
	
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

	}
	
	private HttpResponse<Object> when_0() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.CreateTodoPayload payload_0 = objectMapper.readValue("{" +
			"\"description\" : \"" + this.randomString() + " " + this.getTestId() + "\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
				com.anfelisa.todo.data.CreateTodoPayload.class);
		com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
		"\"uuid\" : \"" + uuid + "\"," + 
		"\"description\" : \"" + this.randomString() + " " + this.getTestId() + "\"," + 
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
	
	private void then_0(HttpResponse<Object> response) throws Exception {
		if (response.getStatusCode() == 500) {
			String statusMessage = response.getStatusMessage() != null ? response.getStatusMessage() : "";
			String errorMessage = "status " + response.getStatusCode() + " failed: " + statusMessage;
			LOG.error("THEN: " + errorMessage);
			assertFail(errorMessage);
		}
		if (response.getStatusCode() != 200) {
			String statusMessage = response.getStatusMessage() != null ? response.getStatusMessage() : "";
			String errorMessage = "status " + response.getStatusCode() + " failed, expected 200: " + statusMessage;
			LOG.error("THEN: " + errorMessage);
			assertFail(errorMessage);
		} else {
			LOG.info("THEN: status 200 passed");
		}
		
	}
			
	@Override
	public void runTest() throws Exception {
		given();
		
		if (prerequisite("CreateRandomTodo")) {
			
				HttpResponse<Object> response_0 = when_0();
				then_0(response_0);
				
		
		} else {
			LOG.info("WHEN: prerequisite for CreateRandomTodo not met");
		}
		
			
	}
	
	
		
	@Override
	protected String scenarioName() {
		return "CreateRandomTodo";
	}
	
}



/******* S.D.G. *******/



