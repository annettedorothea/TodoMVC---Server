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
 * generated with de.acegen 0.9.10
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
public abstract class AbstractGetAllTodosScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractGetAllTodosScenario.class);
	
	private Map<String, Object> extractedValues = new HashMap<String, Object>();
	
	private void given() throws Exception {
		Response response;
		String uuid;
		long timeBeforeRequest;
		long timeAfterRequest;
		
		if (prerequisite("CreateTodo")) {
			uuid = "" + this.getTestId() + "";
			this.callNotReplayableDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 16:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
			com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
				"\"uuid\" : \"" + uuid + "\"," + 
					"\"description\" : \"todo " + this.getTestId() + "\"} ",
			com.anfelisa.todo.data.TodoData.class);
			timeBeforeRequest = System.currentTimeMillis();
			response = 
			this.httpPost(
				"/todos/create", 
				data_0,
				null
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
		} else {
			LOG.info("GIVEN: prerequisite for CreateTodo not met");
		}

	}
	
	private Response when() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.TodoListData data_0 = objectMapper.readValue("{ \"uuid\" : \"" + uuid + "\"}",
		com.anfelisa.todo.data.TodoListData.class);
		long timeBeforeRequest = System.currentTimeMillis();
		Response response = 
		this.httpGet(
			"/todos/all?uuid=" + data_0.getUuid() + "", 
			null
		);
		
		long timeAfterRequest = System.currentTimeMillis();
		LOG.info("WHEN: GetAllTodos finished in {} ms", (timeAfterRequest-timeBeforeRequest));
		addToMetrics("GetAllTodos", (timeAfterRequest-timeBeforeRequest));
		return response;
	}
	
	private com.anfelisa.todo.data.GetAllTodosResponse then(Response response) throws Exception {
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
		
		com.anfelisa.todo.data.GetAllTodosResponse actual = null;
		try {
			actual = response.readEntity(com.anfelisa.todo.data.GetAllTodosResponse.class);
			
		} catch (Exception x) {
			LOG.error("THEN: failed to read response", x);
			assertFail(x.getMessage());
		}
		com.anfelisa.todo.data.TodoListData expectedData = objectMapper.readValue("{" +
			"\"uuid\" : \"\"," + 
				"\"todoList\" : [ { \"createdDateTime\" : \"2020-07-07T16:30\"," + 
				"\"description\" : \"todo " + this.getTestId() + "\"," + 
				"\"done\" : false," + 
				"\"id\" : \"" + this.getTestId() + "\"," + 
				"\"updatedDateTime\" : null}]} ",
		com.anfelisa.todo.data.TodoListData.class);
		
		com.anfelisa.todo.data.GetAllTodosResponse expected = new com.anfelisa.todo.data.GetAllTodosResponse(expectedData);


		assertThat(actual, expected);
		
		LOG.info("THEN: response passed");
		
		return actual;
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("GetAllTodos")) {
			Response response = when();

			com.anfelisa.todo.data.GetAllTodosResponse actualResponse = then(response);
			
	
		} else {
			LOG.info("WHEN: prerequisite for GetAllTodos not met");
		}
	}
	
	
		
	@Override
	protected String scenarioName() {
		return "GetAllTodos";
	}
	
}



/******* S.D.G. *******/



