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
 * generated with de.acegen 0.9.8
 *
 */




package com.anfelisa.todo.actions;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acegen.IDataContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.anfelisa.todo.data.*;
import com.fasterxml.jackson.databind.DeserializationFeature;		

public class AceDataFactory {
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static final Logger LOG = LoggerFactory.getLogger(AceDataFactory.class);

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static IDataContainer createAceData(String className, String json) {
		try {
			if (className.equals("com.anfelisa.todo.actions.GetAllTodosAction")) {
				TodoListData data = mapper.readValue(json, TodoListData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.GetTodoAction")) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.CreateTodoAction") ||
					className.equals("com.anfelisa.todo.commands.CreateTodoCommand") ||
					className.equals("com.anfelisa.todo.events.CreateTodoSuccessEvent")
			) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.ToggleTodoAction") ||
					className.equals("com.anfelisa.todo.commands.ToggleTodoCommand") ||
					className.equals("com.anfelisa.todo.events.ToggleTodoSuccessEvent")
			) {
				TodoToggleData data = mapper.readValue(json, TodoToggleData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.ToggleAllAction") ||
					className.equals("com.anfelisa.todo.commands.ToggleAllCommand") ||
					className.equals("com.anfelisa.todo.events.ToggleAllSuccessEvent")
			) {
				ToggleAllData data = mapper.readValue(json, ToggleAllData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.UpdateTodoAction") ||
					className.equals("com.anfelisa.todo.commands.UpdateTodoCommand") ||
					className.equals("com.anfelisa.todo.events.UpdateTodoSuccessEvent")
			) {
				TodoData data = mapper.readValue(json, TodoData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.DeleteTodoAction") ||
					className.equals("com.anfelisa.todo.commands.DeleteTodoCommand") ||
					className.equals("com.anfelisa.todo.events.DeleteTodoSuccessEvent")
			) {
				TodoIdData data = mapper.readValue(json, TodoIdData.class);
				data.migrateLegacyData(json);
				return data;
			}
			if (className.equals("com.anfelisa.todo.actions.ClearDoneAction") ||
					className.equals("com.anfelisa.todo.commands.ClearDoneCommand") ||
					className.equals("com.anfelisa.todo.events.ClearDoneSuccessEvent")
			) {
				ClearDoneData data = mapper.readValue(json, ClearDoneData.class);
				data.migrateLegacyData(json);
				return data;
			}
		} catch (IOException e) {
			LOG.error("failed to create ace data {} with data {}", className, json, e);
		}

		return null;
	}
}



/******* S.D.G. *******/



