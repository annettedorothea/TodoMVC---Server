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




/******* S.D.G. *******/



