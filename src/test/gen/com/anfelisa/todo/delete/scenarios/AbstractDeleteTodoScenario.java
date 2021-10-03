/********************************************************************************
 * generated by de.acegen 1.5.6
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
import de.acegen.SquishyDataProvider;
import de.acegen.HttpResponse;

@SuppressWarnings("unused")
public abstract class AbstractDeleteTodoScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractDeleteTodoScenario.class);
	
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
			this.callSquishyDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 16:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
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

	}
	
	private HttpResponse<Object> when() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.TodoIdData data_0 = objectMapper.readValue("{" +
		"\"uuid\" : \"" + uuid + "\"," + 
		"\"id\" : \"" + this.getTestId() + "\"} ",
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
			
		if (prerequisite("DeleteTodo")) {
			HttpResponse<Object> response = when();

			then(response);
			
			this.todoWasDeleted();
	
		} else {
			LOG.info("WHEN: prerequisite for DeleteTodo not met");
		}
	}
	
	
	private void todoWasDeleted() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.getTestId() + "");
		
		assertIsNull(actual);
	
		LOG.info("THEN: todoWasDeleted passed");
	}
		
	@Override
	protected String scenarioName() {
		return "DeleteTodo";
	}
	
}



/******* S.D.G. *******/



