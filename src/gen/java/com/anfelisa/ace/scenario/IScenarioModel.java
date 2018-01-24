package com.anfelisa.ace.scenario;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=ScenarioModel.class)
public interface IScenarioModel {

	Integer getId();
	String getDescription();
	String getTimeline();
	String getCreator();
	org.joda.time.DateTime getCreatedDateTime();
	org.joda.time.DateTime getUpdatedDateTime();
	String getServerVersion();
	String getClientVersion();
	String getDevice();


}

/*       S.D.G.       */

