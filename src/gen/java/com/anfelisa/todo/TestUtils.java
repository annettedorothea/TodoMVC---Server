package com.anfelisa.todo;

import com.anfelisa.ace.JodaObjectMapper;
import com.anfelisa.ace.TimelineItem;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TestUtils {

	private static final JodaObjectMapper mapper = new JodaObjectMapper();

	
	public static TimelineItem createCreateTodoSuccessEventTimelineItem(com.anfelisa.todo.data.ITodoData data) throws JsonProcessingException {
		String json = mapper.writeValueAsString(data);
		return new TimelineItem("prepare", null, "com.anfelisa.todo.events.CreateTodoSuccessEvent", null, json, data.getUuid());
	}
	
	public static TimelineItem createToggleTodoSuccessEventTimelineItem(com.anfelisa.todo.data.ITodoToggleData data) throws JsonProcessingException {
		String json = mapper.writeValueAsString(data);
		return new TimelineItem("prepare", null, "com.anfelisa.todo.events.ToggleTodoSuccessEvent", null, json, data.getUuid());
	}
	
	public static TimelineItem createToggleAllSuccessEventTimelineItem(com.anfelisa.todo.data.IToggleAllData data) throws JsonProcessingException {
		String json = mapper.writeValueAsString(data);
		return new TimelineItem("prepare", null, "com.anfelisa.todo.events.ToggleAllSuccessEvent", null, json, data.getUuid());
	}
	
	public static TimelineItem createUpdateTodoSuccessEventTimelineItem(com.anfelisa.todo.data.ITodoData data) throws JsonProcessingException {
		String json = mapper.writeValueAsString(data);
		return new TimelineItem("prepare", null, "com.anfelisa.todo.events.UpdateTodoSuccessEvent", null, json, data.getUuid());
	}
	
	public static TimelineItem createDeleteTodoSuccessEventTimelineItem(com.anfelisa.todo.data.ITodoIdData data) throws JsonProcessingException {
		String json = mapper.writeValueAsString(data);
		return new TimelineItem("prepare", null, "com.anfelisa.todo.events.DeleteTodoSuccessEvent", null, json, data.getUuid());
	}
	
	public static TimelineItem createClearDoneSuccessEventTimelineItem(com.anfelisa.todo.data.IClearDoneData data) throws JsonProcessingException {
		String json = mapper.writeValueAsString(data);
		return new TimelineItem("prepare", null, "com.anfelisa.todo.events.ClearDoneSuccessEvent", null, json, data.getUuid());
	}
	
	
}

