package com.anfelisa.ace;

import java.util.List;

import org.joda.time.DateTime;

public class E2E {
	
	public static boolean sessionIsRunning;
	
	public static DateTime sessionStartedAt;
	
	public static List<ITimelineItem> timeline;
	
	public static ITimelineItem selectAction(String uuid) {
		for (ITimelineItem timelineItem : timeline) {
			if (timelineItem.getUuid().equals(uuid) && timelineItem.getType().equals("action")) {
				return timelineItem;
			}
		}
		return null;
	}
	
	public static ITimelineItem selectEvent(String uuid) {
		for (ITimelineItem timelineItem : timeline) {
			if (timelineItem.getUuid().equals(uuid) && timelineItem.getType().equals("event")) {
				return timelineItem;
			}
		}
		return null;
	}

	
	public static ITimelineItem selectNextAction(String uuid) {
		if (uuid != null) {
			boolean returnNextAction = false;
			for (ITimelineItem timelineItem : timeline) {
				if (returnNextAction && timelineItem.getType().equals("action")) {
					return timelineItem;
				}
				if (timelineItem.getUuid().equals(uuid) && timelineItem.getType().equals("action")) {
					returnNextAction = true;
				}
			}
		} else {
			for (ITimelineItem timelineItem : timeline) {
				if (timelineItem.getType().equals("action")) {
					return timelineItem;
				}
			}
		}
		return null;
	}

	
}
