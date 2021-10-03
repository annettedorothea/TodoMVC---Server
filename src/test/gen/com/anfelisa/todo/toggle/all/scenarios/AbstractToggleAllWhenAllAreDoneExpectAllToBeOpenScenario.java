/********************************************************************************
 * generated by de.acegen 1.5.5
 ********************************************************************************/




package com.anfelisa.todo.toggle.all.scenarios;

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
public abstract class AbstractToggleAllWhenAllAreDoneExpectAllToBeOpenScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleAllWhenAllAreDoneExpectAllToBeOpenScenario.class);
	
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

		if (prerequisite("CreateSecondTodo")) {
			uuid = "" + this.getTestId() + "_2";
			this.callSquishyDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 16:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
			com.anfelisa.todo.data.CreateTodoPayload payload_2 = objectMapper.readValue("{" +
				"\"description\" : \"todo 2 " + this.getTestId() + "\"," + 
				"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.CreateTodoPayload.class);
			com.anfelisa.todo.data.TodoData data_2 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
			"\"description\" : \"todo 2 " + this.getTestId() + "\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.TodoData.class);
			HttpResponse<Object> response_2 = 
			this.httpPost(
				"/todos/create", 
			 	payload_2,
				null,
				uuid,
				null
			);
			
			if (response_2.getStatusCode() >= 400) {
				String statusMessage = response_2.getStatusMessage() != null ? response_2.getStatusMessage() : "";
				String message = "GIVEN CreateSecondTodo fails\n" + statusMessage;
				LOG.error("GIVEN: CreateSecondTodo fails due to {} in {} ms", message, response_2.getDuration());
				assertFail(message);
			}
			LOG.info("GIVEN: CreateSecondTodo success in {} ms", response_2.getDuration());
			addToMetrics("CreateTodo", response_2.getDuration());
		} else {
			LOG.info("GIVEN: prerequisite for CreateSecondTodo not met");
		}

		if (prerequisite("ToggleAllWhenNoneIsDoneExpectAllToBeDone")) {
			uuid = this.randomUUID();
			this.callSquishyDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 17:20", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
			com.anfelisa.todo.data.ToggleAllData data_3 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
					com.anfelisa.todo.data.ToggleAllData.class);
			HttpResponse<Object> response_3 = 
			this.httpPut(
				"/todos/toggle-all?categoryId=" + data_3.getCategoryId() + "", 
			 	null,
				null,
				uuid,
				null
			);
			
			if (response_3.getStatusCode() >= 400) {
				String statusMessage = response_3.getStatusMessage() != null ? response_3.getStatusMessage() : "";
				String message = "GIVEN ToggleAllWhenNoneIsDoneExpectAllToBeDone fails\n" + statusMessage;
				LOG.error("GIVEN: ToggleAllWhenNoneIsDoneExpectAllToBeDone fails due to {} in {} ms", message, response_3.getDuration());
				assertFail(message);
			}
			LOG.info("GIVEN: ToggleAllWhenNoneIsDoneExpectAllToBeDone success in {} ms", response_3.getDuration());
			addToMetrics("ToggleAll", response_3.getDuration());
		} else {
			LOG.info("GIVEN: prerequisite for ToggleAllWhenNoneIsDoneExpectAllToBeDone not met");
		}

	}
	
	private HttpResponse<Object> when() throws Exception {
		String uuid = this.randomUUID();
		this.callSquishyDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 17:20", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
		com.anfelisa.todo.data.ToggleAllData data_0 = objectMapper.readValue("{" +
		"\"uuid\" : \"" + uuid + "\"," + 
		"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
				com.anfelisa.todo.data.ToggleAllData.class);
		HttpResponse<Object> response = 
		this.httpPut(
			"/todos/toggle-all?categoryId=" + data_0.getCategoryId() + "", 
		 	null,
			null,
			uuid,
			null
		);
		
		LOG.info("WHEN: ToggleAll finished in {} ms", response.getDuration());
		if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
			addToMetrics("ToggleAll", response.getDuration());
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
			
		if (prerequisite("ToggleAllWhenAllAreDoneExpectAllToBeOpen")) {
			HttpResponse<Object> response = when();

			then(response);
			
			this.todoIsOpen();
			this.secondTodoIsOpen();
	
		} else {
			LOG.info("WHEN: prerequisite for ToggleAllWhenAllAreDoneExpectAllToBeOpen not met");
		}
	}
	
	
	private void todoIsOpen() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.getTestId() + "");
		
		com.anfelisa.todo.models.ITodoModel expected = objectMapper.readValue("{" +
			"\"createdDateTime\" : \"2020-07-07T16:30\"," + 
			"\"description\" : \"todo " + this.getTestId() + "\"," + 
			"\"done\" : false," + 
			"\"id\" : \"" + this.getTestId() + "\"," + 
			"\"updatedDateTime\" : \"2020-07-07T17:20\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
		com.anfelisa.todo.models.TodoModel.class);
		assertThat(actual, expected);
	
		LOG.info("THEN: todoIsOpen passed");
	}
	private void secondTodoIsOpen() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.getTestId() + "_2");
		
		com.anfelisa.todo.models.ITodoModel expected = objectMapper.readValue("{" +
			"\"createdDateTime\" : \"2020-07-07T16:30\"," + 
			"\"description\" : \"todo 2 " + this.getTestId() + "\"," + 
			"\"done\" : false," + 
			"\"id\" : \"" + this.getTestId() + "_2\"," + 
			"\"updatedDateTime\" : \"2020-07-07T17:20\"," + 
			"\"categoryId\" : \"category_" + this.getTestId() + "\"} ",
		com.anfelisa.todo.models.TodoModel.class);
		assertThat(actual, expected);
	
		LOG.info("THEN: secondTodoIsOpen passed");
	}
		
	@Override
	protected String scenarioName() {
		return "ToggleAllWhenAllAreDoneExpectAllToBeOpen";
	}
	
}



/******* S.D.G. *******/



