/* 
 * Copyright (c) 2020, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * generated with de.acegen 0.9.9
 *
 */




package com.anfelisa.todo.scenarios;

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
import de.acegen.NotReplayableDataProvider;

@SuppressWarnings("unused")
public abstract class AbstractCreateRandomTodoScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractCreateRandomTodoScenario.class);
	
	private Map<String, Object> extractedValues = new HashMap<String, Object>();
	
	private void given() throws Exception {
		Response response;
		String uuid;
		long timeBeforeRequest;
		long timeAfterRequest;
	}
	
	private Response when() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
				"\"description\" : \"" + this.randomString() + " " + this.getTestId() + "\"} ",
		com.anfelisa.todo.data.TodoData.class);
		long timeBeforeRequest = System.currentTimeMillis();
		Response response = 
		this.httpPost(
			"/todos/create", 
			data_0,
			null
		);
		
		long timeAfterRequest = System.currentTimeMillis();
		LOG.info("WHEN: CreateTodo finished in {} ms", (timeAfterRequest-timeBeforeRequest));
		addToMetrics("CreateTodo", (timeAfterRequest-timeBeforeRequest));
		return response;
	}
	
	private com.anfelisa.todo.data.CreateTodoResponse then(Response response) throws Exception {
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
		
		com.anfelisa.todo.data.CreateTodoResponse actual = null;
		try {
			actual = response.readEntity(com.anfelisa.todo.data.CreateTodoResponse.class);
			
			try {
				
				Object todoId = this.extractTodoId(actual);
				extractedValues.put("todoId", todoId);
				LOG.info("THEN: extracted " + todoId.toString()  + " as todoId");
				
				Object createdDateTime = this.extractCreatedDateTime(actual);
				extractedValues.put("createdDateTime", createdDateTime);
				LOG.info("THEN: extracted " + createdDateTime.toString()  + " as createdDateTime");
				
				Object description = this.extractDescription(actual);
				extractedValues.put("description", description);
				LOG.info("THEN: extracted " + description.toString()  + " as description");
			} catch (Exception x) {
				LOG.info("THEN: failed to extract values from response ", x);
			}
		} catch (Exception x) {
		}
		
		return actual;
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("CreateRandomTodo")) {
			Response response = when();

			com.anfelisa.todo.data.CreateTodoResponse actualResponse = then(response);
			
			this.todoWasCreated();
		
		} else {
			LOG.info("WHEN: prerequisite for CreateRandomTodo not met");
		}
	}
	
	
	private void todoWasCreated() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.extractedValues.get("todoId").toString() + "");
		
		com.anfelisa.todo.models.ITodoModel expected = objectMapper.readValue("{" +
			"\"createdDateTime\" : \"" + LocalDateTime.parse(this.extractedValues.get("createdDateTime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))  + "\"," + 
				"\"description\" : \"" + this.extractedValues.get("description").toString() + "\"," + 
				"\"done\" : false," + 
				"\"id\" : \"" + this.extractedValues.get("todoId").toString() + "\"," + 
				"\"updatedDateTime\" : null} ",
		com.anfelisa.todo.models.TodoModel.class);
		assertThat(actual, expected);

		LOG.info("THEN: todoWasCreated passed");
	}
	
	@Override
	protected String scenarioName() {
		return "CreateRandomTodo";
	}

}



/******* S.D.G. *******/


			
