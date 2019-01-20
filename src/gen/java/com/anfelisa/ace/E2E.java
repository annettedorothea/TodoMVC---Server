package com.anfelisa.ace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

public class E2E {

	public static boolean sessionIsRunning;

	public static DateTime sessionStartedAt;

	private static Map<String, AceOperation> timeline;

	private static List<String> uuidList;

	private static int index;

	public static void reset() {
		E2E.sessionIsRunning = false;
		E2E.sessionStartedAt = null;
		E2E.timeline = null;
		E2E.index = 0;
	}

	public static void init(List<ITimelineItem> initialTimeline) {
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
		E2E.sessionIsRunning = true;
		E2E.sessionStartedAt = new DateTime(System.currentTimeMillis());
		E2E.index = 0;
	}

	public static ITimelineItem selectNextAction() {
		if (index < uuidList.size()) {
			String uuid = uuidList.get(index);
			index++;
			return timeline.get(uuid).getAction();
		}
		return null;
	}

	public static ITimelineItem selectAction(String uuid) {
		return timeline.get(uuid).getAction();
	}

	public static ITimelineItem selectEvent(String uuid) {
		return timeline.get(uuid).getEvent();
	}
	
}

