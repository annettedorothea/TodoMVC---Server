package com.anfelisa.ace;

public class EventFactory {
	public static IEvent createEvent(String eventClass, String json, IDaoProvider daoProvider,
			ViewProvider viewProvider) {
		if (eventClass.startsWith("com.anfelisa.todo.events")) {
			return com.anfelisa.todo.events.EventFactory.createEvent(eventClass, json, daoProvider, viewProvider);
		}
		return null;
	}

	public static IEvent createEvent(String eventClass, IDataContainer data, IDaoProvider daoProvider,
			ViewProvider viewProvider) {
		if (eventClass.startsWith("com.anfelisa.todo.events")) {
			return com.anfelisa.todo.events.EventFactory.createEvent(eventClass, data, daoProvider,
					viewProvider);
		}
		return null;
	}
}
