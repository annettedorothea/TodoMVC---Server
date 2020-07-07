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
 * generated with de.acegen 0.9.5
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
public abstract class AbstractCreateTodoScenario extends BaseScenario {

	static final Logger LOG = LoggerFactory.getLogger(AbstractCreateTodoScenario.class);
	
	private void given() throws Exception {
		Response response;
		String uuid;
		long timeBeforeRequest;
		long timeAfterRequest;
	}
	
	private Response when() throws Exception {
		String uuid = "" + this.getTestId() + "";
		this.callNotReplayableDataProviderPutSystemTime(uuid, LocalDateTime.parse("20200707 16:30", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
		com.anfelisa.todo.data.TodoData data_0 = objectMapper.readValue("{" +
			"\"uuid\" : \"" + uuid + "\"," + 
				"\"description\" : \"todo " + this.getTestId() + "\"} ",
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
	
	private void then(Response response) throws Exception {
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
		
		
	}
			
	@Override
	public void runTest() throws Exception {
		given();
			
		if (prerequisite("CreateTodo")) {
			Response response = when();

			then(response);
			
			this.todoWasCreated();
		
		} else {
			LOG.info("WHEN: prerequisite for CreateTodo not met");
		}
	}
	
	
	private void todoWasCreated() throws Exception {
		com.anfelisa.todo.models.ITodoModel actual = daoProvider.getTodoDao().selectByPrimaryKey(handle, "" + this.getTestId() + "");
		
		com.anfelisa.todo.models.ITodoModel expected = objectMapper.readValue("{" +
			"\"createdDateTime\" : \"2020-07-07T16:30\"," + 
				"\"description\" : \"todo " + this.getTestId() + "\"," + 
				"\"done\" : false," + 
				"\"id\" : \"" + this.getTestId() + "\"," + 
				"\"updatedDateTime\" : null} ",
		com.anfelisa.todo.models.TodoModel.class);
		assertThat(actual, expected);

		LOG.info("THEN: todoWasCreated passed");
	}
	
	@Override
	protected String scenarioName() {
		return "CreateTodo";
	}

}



/******* S.D.G. *******/


			
