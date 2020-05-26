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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class E2E {

	private boolean sessionIsRunning;

	private LocalDateTime sessionStartedAt;

	private Map<String, AceOperation> timeline;

	private List<String> uuidList;

	private int index;
	
	private List<Thread> triggerdThreads;

	static final Logger LOG = LoggerFactory.getLogger(E2E.class);

	public E2E() {
		this.sessionIsRunning = false;
		this.sessionStartedAt = null;
		this.timeline = null;
		this.index = 0;
		this.triggerdThreads = new ArrayList<>();
	}
	
	public void addTriggeredThread(Thread thread) {
		triggerdThreads.add(thread);
	}
	
	public boolean isSessionRunning() {
		return this.sessionIsRunning;
	}

	public LocalDateTime getSessionStartedAt() {
		return this.sessionStartedAt;
	}

	public void reset() {
		for (Thread thread : triggerdThreads) {
			try {
				LOG.info("wait for thread {} to finish before resetting E2E session", thread.getName());
				thread.join();
			} catch (InterruptedException e) {
				LOG.error("thread.join {} was interrupted", thread.getName(), e);
			}
		}
		this.sessionIsRunning = false;
		this.sessionStartedAt = null;
		this.timeline = null;
		this.index = 0;
		this.triggerdThreads.clear();
	}


	public void init(List<ITimelineItem> initialTimeline) {
		timeline = new HashMap<>();
		uuidList = new ArrayList<>();
		for (ITimelineItem timelineItem : initialTimeline) {
			String uuid = timelineItem.getUuid();
			if (!uuidList.contains(uuid)) {
				uuidList.add(uuid);
			}
			AceOperation aceOperation = timeline.get(uuid);
			if (aceOperation == null) {
				aceOperation = new AceOperation(uuid);
				timeline.put(uuid, aceOperation);
			}
			if ("action".equals(timelineItem.getType())) {
				aceOperation.setAction(timelineItem);
			} else if ("command".equals(timelineItem.getType())) {
				aceOperation.setCommand(timelineItem);
			} else if ("event".equals(timelineItem.getType())) {
				aceOperation.setEvent(timelineItem);
			}
		}
		this.sessionIsRunning = true;
		this.sessionStartedAt = LocalDateTime.ofEpochSecond(System.currentTimeMillis(), 0, null);
		this.index = 0;
	}

	public ITimelineItem selectNextAction() {
		if (index < uuidList.size()) {
			String uuid = uuidList.get(index);
			index++;
			return timeline.get(uuid).getAction();
		}
		return null;
	}

	public ITimelineItem selectAction(String uuid) {
		return timeline.get(uuid).getAction();
	}

	public ITimelineItem selectCommand(String uuid) {
		return timeline.get(uuid).getCommand();
	}

	public ITimelineItem selectEvent(String uuid) {
		return timeline.get(uuid).getEvent();
	}
	
}




/******* S.D.G. *******/



