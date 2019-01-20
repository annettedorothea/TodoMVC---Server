package com.anfelisa.ace;

public class EventFactory {
	public static IEvent createEvent(String eventClass, String json, DatabaseHandle databaseHandle,
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		if (eventClass.startsWith("com.anfelisa.todo.events")) {
			return com.anfelisa.todo.events.EventFactory.createEvent(eventClass, json, databaseHandle, daoProvider,
					viewProvider);
		}
		return null;
	}
	public static IEvent createEvent(String eventClass, IDataContainer data, DatabaseHandle databaseHandle,
			IDaoProvider daoProvider, ViewProvider viewProvider) {
		if (eventClass.startsWith("com.anfelisa.todo.events")) {
			return com.anfelisa.todo.events.EventFactory.createEvent(eventClass, data, databaseHandle, daoProvider,
					viewProvider);
		}
		return null;
	}
}

