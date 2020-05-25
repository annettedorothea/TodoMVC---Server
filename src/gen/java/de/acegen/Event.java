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




package de.acegen;

import java.util.List;

public abstract class Event<T extends IDataContainer> implements IEvent {

	private T eventData;
	private String eventName;
	private IDaoProvider daoProvider;
	private ViewProvider viewProvider;
	private CustomAppConfiguration appConfiguration;

	public Event(String eventName, T eventData, IDaoProvider daoProvider, ViewProvider viewProvider, CustomAppConfiguration appConfiguration) {
		super();
		this.eventData = eventData;
		this.eventName = eventName;
		this.daoProvider = daoProvider;
		this.viewProvider = viewProvider;
		this.appConfiguration = appConfiguration;
	}

	public void notifyListeners(PersistenceHandle handle) {
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

	public void publish(PersistenceHandle handle, PersistenceHandle timelineHandle) {
		if (appConfiguration.getConfig().writeTimeline()) {
			daoProvider.getAceDao().addEventToTimeline(this, timelineHandle);
		}
		this.notifyListeners(handle);
	}

}




/******* S.D.G. *******/



