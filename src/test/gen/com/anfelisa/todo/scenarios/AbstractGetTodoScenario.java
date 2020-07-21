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
 * generated with de.acegen 0.9.7
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
public abstract class AbstractGetTodoScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractGetTodoScenario.class);
	
	private Map<String, Object> extractedValues = new HashMap<String, Object>();
	
	private void given() throws Exception {
		Response response;
		String uuid;
		long timeBeforeRequest;
		long timeAfterRequest;
		if (prerequisite("CreateRandomTodo")) {
			uuid = this.randomUUID();
			com.anfelisa.todo.data.TodoData data_1 = objectMapper.readValue("{" +
				"\"uuid\" : \"" + uuid + "\"," + 
					"\"description\" : \"" + this.randomString() + " " + this.getTestId() + "\"} ",
			com.anfelisa.todo.data.TodoData.class);
			timeBeforeRequest = System.currentTimeMillis();
			response = 
			this.httpPost(
				"/todos/create", 
				data_1,
				null
			);
			
			timeAfterRequest = System.currentTimeMillis();
			if (response.getStatus() >= 400) {
				String message = "GIVEN CreateRandomTodo fails\n" + response.readEntity(String.class);
				LOG.info("GIVEN: CreateRandomTodo fails due to {} in {} ms", message, (timeAfterRequest-timeBeforeRequest));
				addToMetrics("CreateTodo", (timeAfterRequest-timeBeforeRequest));
				assertFail(message);
			}
			LOG.info("GIVEN: CreateRandomTodo success in {} ms", (timeAfterRequest-timeBeforeRequest));
			addToMetrics("CreateTodo", (timeAfterRequest-timeBeforeRequest));
			com.anfelisa.todo.data.CreateTodoResponse responseEntity_1 = null;
			try {
				responseEntity_1 = response.readEntity(com.anfelisa.todo.data.CreateTodoResponse.class);
				
				Object todoId = this.extractTodoId(responseEntity_1);
				extractedValues.put("todoId", todoId);
				LOG.info("GIVEN: extracted " + todoId.toString()  + " as todoId");
				
				Object createdDateTime = this.extractCreatedDateTime(responseEntity_1);
				extractedValues.put("createdDateTime", createdDateTime);
				LOG.info("GIVEN: extracted " + createdDateTime.toString()  + " as createdDateTime");
				
				Object description = this.extractDescription(responseEntity_1);
				extractedValues.put("description", description);
				LOG.info("GIVEN: extracted " + description.toString()  + " as description");
			} catch (Exception x) {
				LOG.info("GIVEN: failed to extract values from response ", x);
			}
		} else {
			LOG.info("GIVEN: prerequisite for CreateRandomTodo not met");
		}
		

	}
	
	private Response when() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
				"\"id\" : \"" + this.extractedValues.get("todoId").toString() + "\"} ",
		com.anfelisa.todo.data.TodoData.class);
		long timeBeforeRequest = System.currentTimeMillis();
		Response response = 
		this.httpGet(
			"/todos/" + data_0.getId() + "?uuid=" + data_0.getUuid() + "", 
			null
		);
		
		long timeAfterRequest = System.currentTimeMillis();
		LOG.info("WHEN: GetTodo finished in {} ms", (timeAfterRequest-timeBeforeRequest));
		addToMetrics("GetTodo", (timeAfterRequest-timeBeforeRequest));
		return response;
	}
	
	private com.anfelisa.todo.data.GetTodoResponse then(Response response) throws Exception {
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
		
		com.anfelisa.todo.data.GetTodoResponse actual = null;
		try {
			actual = response.readEntity(com.anfelisa.todo.data.GetTodoResponse.class);
		} catch (Exception x) {
		}
		com.anfelisa.todo.data.TodoData expectedData = objectMapper.readValue("{" +
			"\"uuid\" : \"\"," + 
				"\"createdDateTime\" : \"" + LocalDateTime.parse(this.extractedValues.get("createdDateTime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))  + "\"," + 
				"\"description\" : \"" + this.extractedValues.get("description").toString() + "\"," + 
				"\"done\" : false," + 
				"\"id\" : \"" + this.extractedValues.get("todoId").toString() + "\"} ",
		com.anfelisa.todo.data.TodoData.class);
		
		com.anfelisa.todo.data.GetTodoResponse expected = new com.anfelisa.todo.data.GetTodoResponse(expectedData);


		assertThat(actual, expected);
		
		LOG.info("THEN: response passed");
		
		return actual;
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("GetTodo")) {
			Response response = when();

			com.anfelisa.todo.data.GetTodoResponse actualResponse = then(response);
			
		
		} else {
			LOG.info("WHEN: prerequisite for GetTodo not met");
		}
	}
	
	
	
	@Override
	protected String scenarioName() {
		return "GetTodo";
	}

}



/******* S.D.G. *******/


			
