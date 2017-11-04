package com.anfelisa.ace;

import org.joda.time.DateTime;

public interface ITimelineItem {

	Integer getId();
	
	String getType();
	
	String getMethod();
	
	String getName();
	
	DateTime getTimestamp();
	
	String getData();
	
	String getUuid();
}

