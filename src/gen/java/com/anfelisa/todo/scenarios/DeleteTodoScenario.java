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
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import org.junit.Test;

import com.anfelisa.ace.BaseScenario;
import com.anfelisa.ace.ITimelineItem;
import com.anfelisa.todo.TestUtils;

public class DeleteTodoScenario extends BaseScenario {

	private void given() throws Exception {
		List<ITimelineItem> timeline = new ArrayList<>();
		
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(
			new com.anfelisa.todo.data.TodoData("")
			.withId("123")
			.withDescription("todo 1")
			.withCreatedDateTime(DateTime.parse("10.10.2019 8:22:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")))
		));
		
		timeline.add(TestUtils.createCreateTodoSuccessEventTimelineItem(
			new com.anfelisa.todo.data.TodoData("")
			.withId("234")
			.withDescription("todo 2")
			.withCreatedDateTime(DateTime.parse("10.10.2019 9:01:00", DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")))
		));
		
		prepare(timeline, DROPWIZARD.getLocalPort());
	}
	
	@Test
	public void execute() throws Exception {
		given();

	}
	
	
}




/******* S.D.G. *******/



