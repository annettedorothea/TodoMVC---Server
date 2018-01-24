package com.anfelisa.ace.bug;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=BugModel.class)
public interface IBugModel {

	Integer getId();
	String getDescription();
	String getTimeline();
	String getReporter();
	Boolean getResolved();
	org.joda.time.DateTime getCreatedDateTime();
	org.joda.time.DateTime getUpdatedDateTime();
	String getServerVersion();
	String getClientVersion();
	String getDevice();


}

/*       S.D.G.       */

