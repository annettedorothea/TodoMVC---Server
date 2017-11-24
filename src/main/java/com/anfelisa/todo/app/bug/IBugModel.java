package com.anfelisa.todo.app.bug;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=BugModel.class)
public interface IBugModel {

	Integer getId();
	String getDescription();
	String getData();
	String getReporter();
	Boolean getResolved();
	org.joda.time.DateTime getCreatedDateTime();
	org.joda.time.DateTime getUpdatedDateTime();


}

/*       S.D.G.       */
