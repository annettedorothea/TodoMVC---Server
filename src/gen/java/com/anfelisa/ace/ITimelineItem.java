package com.anfelisa.ace;

import org.joda.time.DateTime;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TimelineItem.class)
public interface ITimelineItem {

	Integer getId();
	
	String getType();
	
	String getMethod();
	
	String getName();
	
	DateTime getTimestamp();
	
	String getData();
	
	String getUuid();
}

