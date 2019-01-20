package com.anfelisa.ace;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Event<T extends IDataContainer> implements IEvent {

	static final Logger LOG = LoggerFactory.getLogger(Event.class);

	protected T eventData;
	private String eventName;
	protected DatabaseHandle databaseHandle;
	protected IDaoProvider daoProvider;
	private ViewProvider viewProvider;

	public Event(String eventName, T eventData, DatabaseHandle databaseHandle, IDaoProvider daoProvider,
			ViewProvider viewProvider) {
		super();
		this.eventData = eventData;
		this.eventName = eventName;
		this.databaseHandle = databaseHandle;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
	}

	public void notifyListeners() {
		List<EventConsumer> consumerList = viewProvider.getConsumerForEvent(eventName);
		if (consumerList != null) {
			for (EventConsumer consumer : consumerList) {
				consumer.consumeEvent(this.eventData, databaseHandle.getHandle());
			}
		}
	}

	public IDataContainer getEventData() {
		return eventData;
	}

	public String getEventName() {
		return eventName;
	}

	public DatabaseHandle getDatabaseHandle() {
		return databaseHandle;
	}

	public void publish() {
		daoProvider.addEventToTimeline(this);
		this.notifyListeners();
	}

}

