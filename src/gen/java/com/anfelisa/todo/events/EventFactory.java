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




package com.anfelisa.todo.events;

import com.anfelisa.ace.IDaoProvider;
import com.anfelisa.ace.IEvent;
import com.anfelisa.ace.ViewProvider;
import com.anfelisa.todo.data.*;
import com.anfelisa.ace.JodaObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.anfelisa.ace.IDataContainer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventFactory {
	
	private static JodaObjectMapper mapper = new JodaObjectMapper();
	private static final Logger LOG = LoggerFactory.getLogger(EventFactory.class);

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static IEvent createEvent(String eventClass, String json, IDaoProvider daoProvider, ViewProvider viewProvider) {
		try {
			if (eventClass.equals("com.anfelisa.todo.events.CreateTodoSuccessEvent")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				CreateTodoSuccessEvent event = new CreateTodoSuccessEvent(data, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ToggleTodoSuccessEvent")) {
				TodoToggleData data = mapper.readValue(json, TodoToggleData.class);
				data.migrateLegacyData(json);
				ToggleTodoSuccessEvent event = new ToggleTodoSuccessEvent(data, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ToggleAllSuccessEvent")) {
				ToggleAllData data = mapper.readValue(json, ToggleAllData.class);
				data.migrateLegacyData(json);
				ToggleAllSuccessEvent event = new ToggleAllSuccessEvent(data, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.UpdateTodoSuccessEvent")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				UpdateTodoSuccessEvent event = new UpdateTodoSuccessEvent(data, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.DeleteTodoSuccessEvent")) {
				TodoIdData data = mapper.readValue(json, TodoIdData.class);
				data.migrateLegacyData(json);
				DeleteTodoSuccessEvent event = new DeleteTodoSuccessEvent(data, daoProvider, viewProvider);
				return event;
			}
			if (eventClass.equals("com.anfelisa.todo.events.ClearDoneSuccessEvent")) {
				ClearDoneData data = mapper.readValue(json, ClearDoneData.class);
				data.migrateLegacyData(json);
				ClearDoneSuccessEvent event = new ClearDoneSuccessEvent(data, daoProvider, viewProvider);
				return event;
			}
		} catch (IOException e) {
			LOG.error("failed to create event {} with data {}", eventClass, json, e);
		}

		return null;
	}

	public static IEvent createEvent(String eventClass, IDataContainer data, IDaoProvider daoProvider, ViewProvider viewProvider) {

		if (eventClass.equals("com.anfelisa.todo.events.CreateTodoSuccessEvent")) {
			return new CreateTodoSuccessEvent((TodoData)data, daoProvider, viewProvider);
		}

		if (eventClass.equals("com.anfelisa.todo.events.ToggleTodoSuccessEvent")) {
			return new ToggleTodoSuccessEvent((TodoToggleData)data, daoProvider, viewProvider);
		}

		if (eventClass.equals("com.anfelisa.todo.events.ToggleAllSuccessEvent")) {
			return new ToggleAllSuccessEvent((ToggleAllData)data, daoProvider, viewProvider);
		}

		if (eventClass.equals("com.anfelisa.todo.events.UpdateTodoSuccessEvent")) {
			return new UpdateTodoSuccessEvent((TodoData)data, daoProvider, viewProvider);
		}

		if (eventClass.equals("com.anfelisa.todo.events.DeleteTodoSuccessEvent")) {
			return new DeleteTodoSuccessEvent((TodoIdData)data, daoProvider, viewProvider);
		}

		if (eventClass.equals("com.anfelisa.todo.events.ClearDoneSuccessEvent")) {
			return new ClearDoneSuccessEvent((ClearDoneData)data, daoProvider, viewProvider);
		}


		return null;
	}
}




/******* S.D.G. *******/



