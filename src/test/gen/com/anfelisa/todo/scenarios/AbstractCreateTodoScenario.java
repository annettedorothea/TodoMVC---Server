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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acegen.BaseScenario;
import de.acegen.ITimelineItem;
import de.acegen.NotReplayableDataProvider;

@SuppressWarnings("unused")
public abstract class AbstractCreateTodoScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractCreateTodoScenario.class);
	
	private void given() throws Exception {
		Response response;
		String uuid;
	}
	
	private Response when() throws Exception {
		String uuid = "${testId}".replace("${testId}", this.getTestId());
		com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
				"\"description\" : \"todo " + this.getTestId() + "\"} ",
		com.anfelisa.todo.data.TodoData.class);
		
		return 
		this.httpPost(
			"/todos/create", 
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
						
					if (prerequisite("CreateTodo")) {
						Response response = when();
		
						LOG.info("WHEN: CreateTodo");
				
						then(response);
						
						verifications();
					} else {
						LOG.info("WHEN: prerequisite for CreateTodo not met");
					}
				}
				
				protected abstract void verifications();
				
				@Override
				protected String scenarioName() {
					return "CreateTodo";
				}
			
			}
			
			
			
			/******* S.D.G. *******/
			
			
			
