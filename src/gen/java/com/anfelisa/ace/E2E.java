package com.anfelisa.ace;

import java.util.List;

import org.joda.time.DateTime;

public class E2E {
	
	public static boolean sessionIsRunning;
	
	public static DateTime sessionStartedAt;
	
	public static List<TimelineItem> timeline;
	
	public static TimelineItem selectAction(String uuid) {
		for (TimelineItem timelineItem : timeline) {
			if (timelineItem.getUuid().equals(uuid) && timelineItem.getType().equals("action")) {
				return timelineItem;
			}
		}
		return null;
	}
	
	public static TimelineItem selectEvent(String uuid) {
		for (TimelineItem timelineItem : timeline) {
			if (timelineItem.getUuid().equals(uuid) && timelineItem.getType().equals("event")) {
				return timelineItem;
			}
		}
		return null;
	}

	
	public static TimelineItem selectNextAction(String uuid) {
		if (uuid != null) {
			boolean returnNextAction = false;
			for (TimelineItem timelineItem : timeline) {
				if (timelineItem.getUuid().equals(uuid) && timelineItem.getType().equals("action")) {
					if (returnNextAction) {
						return timelineItem;
					}
					returnNextAction = true;
				}
			}
		} else {
			for (TimelineItem timelineItem : timeline) {
				if (timelineItem.getType().equals("action")) {
					return timelineItem;
				}
			}
		}
		return null;
	}

	
}
