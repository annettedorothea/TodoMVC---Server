/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
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
 */




package com.anfelisa.todo.scenarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acegen.BaseScenario;
import de.acegen.ITimelineItem;
import de.acegen.NotReplayableDataProvider;

@SuppressWarnings("unused")
public abstract class AbstractToggleTodoScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractToggleTodoScenario.class);
	
	private void given() throws Exception {
		Response response;
		String uuid;
		if (prerequisite("CreateTodo")) {
			uuid = "${testId}".replace("${testId}", this.getTestId());
			com.anfelisa.todo.data.TodoData data_1 = objectMapper.readValue("{" +
				"\"uuid\" : \"" + uuid + "\"," + 
					"\"description\" : \"todo " + this.getTestId() + "\"} ",
			com.anfelisa.todo.data.TodoData.class);
			response = 
			this.httpPost(
				"/todos/create", 
				data_1,
				null
			);
			
			if (response.getStatus() >= 400) {
				String message = "GIVEN CreateTodo fails\n" + response.readEntity(String.class);
				LOG.info("GIVEN: CreateTodo fails due to " + message);
				assertFail(message);
			}
			LOG.info("GIVEN: CreateTodo success");
		} else {
			LOG.info("GIVEN: prerequisite for CreateTodo not met");
		}
		

	}
	
	private Response when() throws Exception {
		String uuid = this.randomUUID();
		com.anfelisa.todo.data.TodoToggleData data_0 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
				"\"id\" : \"" + this.getTestId() + "\"} ",
		com.anfelisa.todo.data.TodoToggleData.class);
		
		return 
		this.httpPut(
			"/todos/toggle?uuid=" + data_0.getUuid() + "&id=" + data_0.getId() + "", 
			data_0,
			null
		);
		
	}
	
	private void then(Response response) throws Exception {
		if (response.getStatus() == 500) {
			String message = response.readEntity(String.class);
			assertFail(message);
		}
		if (response.getStatus() != 200) {
			String message = response.readEntity(String.class);
			assertFail(message);
		}
		
			
				}
				
				@Override
				public void runTest() throws Exception {
					given();
						
					if (prerequisite("ToggleTodo")) {
						Response response = when();
		
						LOG.info("WHEN: ToggleTodo");
				
						then(response);
						
						verifications();
					} else {
						LOG.info("WHEN: prerequisite for ToggleTodo not met");
					}
				}
				
				protected abstract void verifications();
				
				@Override
				protected String scenarioName() {
					return "ToggleTodo";
				}
			
			}
			
			
			
			/******* S.D.G. *******/
			
			
			
