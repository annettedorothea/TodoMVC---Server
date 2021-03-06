/********************************************************************************
 * generated by de.acegen 1.2.1
 ********************************************************************************/




package de.acegen;

import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=TimelineItem.class)
public interface ITimelineItem {

	String getType();
	
	String getName();
	
	LocalDateTime getTimestamp();
	
	String getData();
	
	String getUuid();
}



/******* S.D.G. *******/



