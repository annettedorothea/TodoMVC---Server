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
public abstract class AbstractGetTestDataScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractGetTestDataScenario.class);
	
	private Map<String, Object> extractedValues = new HashMap<String, Object>();
	
	private void given() throws Exception {
		Response response;
		String uuid;
		long timeBeforeRequest;
		long timeAfterRequest;
		
	}
	
	private Response when() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.TodoListData data_0 = objectMapper.readValue("{ \"uuid\" : \"" + uuid + "\"}",
		com.anfelisa.todo.data.TodoListData.class);
		long timeBeforeRequest = System.currentTimeMillis();
		Response response = 
		this.httpGet(
			"/todos/ordered?uuid=" + data_0.getUuid() + "", 
			null
		);
		
		long timeAfterRequest = System.currentTimeMillis();
		LOG.info("WHEN: GetOrderedTodos finished in {} ms", (timeAfterRequest-timeBeforeRequest));
		addToMetrics("GetOrderedTodos", (timeAfterRequest-timeBeforeRequest));
		return response;
	}
	
	private com.anfelisa.todo.data.GetOrderedTodosResponse then(Response response) throws Exception {
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
		
				com.anfelisa.todo.data.GetOrderedTodosResponse actual = null;
				if (response.getStatus() < 400) {
					try {
						actual = response.readEntity(com.anfelisa.todo.data.GetOrderedTodosResponse.class);
						
					} catch (Exception x) {
						LOG.error("THEN: failed to read response", x);
						assertFail(x.getMessage());
					}

				}

				return actual;
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("GetTestData")) {
			Response response = when();

			com.anfelisa.todo.data.GetOrderedTodosResponse actualResponse = then(response);
			
	
			justTakeALook(actualResponse);
		} else {
			LOG.info("WHEN: prerequisite for GetTestData not met");
		}
	}
	
	protected abstract void justTakeALook(com.anfelisa.todo.data.GetOrderedTodosResponse response);
	
		
	@Override
	protected String scenarioName() {
		return "GetTestData";
	}
	
}



/******* S.D.G. *******/



