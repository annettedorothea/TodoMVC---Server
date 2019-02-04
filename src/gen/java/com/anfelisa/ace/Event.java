package com.anfelisa.ace;

import java.util.List;

import org.jdbi.v3.core.Handle;

public abstract class Event<T extends IDataContainer> implements IEvent {

	protected T eventData;
	private String eventName;
	protected IDaoProvider daoProvider;
	private ViewProvider viewProvider;

	public Event(String eventName, T eventData, IDaoProvider daoProvider, ViewProvider viewProvider) {
		super();
		this.eventData = eventData;
		this.eventName = eventName;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
	}

	public void notifyListeners(Handle handle) {
		List<EventConsumer> consumerList = viewProvider.getConsumerForEvent(eventName);
		if (consumerList != null) {
			for (EventConsumer consumer : consumerList) {
				consumer.consumeEvent(this.eventData, handle);
			}
		}
	}

	public IDataContainer getEventData() {
		return eventData;
	}

	public String getEventName() {
		return eventName;
	}

	public void publish(Handle handle, Handle timelineHandle) {
		daoProvider.getAceDao().addEventToTimeline(this, timelineHandle);
		this.notifyListeners(handle);
	}

}

