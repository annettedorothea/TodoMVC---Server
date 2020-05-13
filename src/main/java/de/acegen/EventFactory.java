package de.acegen;

public class EventFactory {
	public static IEvent createEvent(String eventClass, String json, IDaoProvider daoProvider,
			ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		if (eventClass.startsWith("com.anfelisa.todo.events")) {
			return com.anfelisa.todo.events.EventFactory.createEvent(eventClass, json, daoProvider, viewProvider,
					appConfiguration);
		}
		return null;
	}

	public static IEvent createEvent(String eventClass, IDataContainer data, IDaoProvider daoProvider,
			ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		if (eventClass.startsWith("com.anfelisa.todo.events")) {
			return com.anfelisa.todo.events.EventFactory.createEvent(eventClass, data, daoProvider, viewProvider,
					appConfiguration);
		}
		return null;
	}
}
