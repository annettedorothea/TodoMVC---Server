package com.anfelisa.todo.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=ScenarioResultModel.class)
public interface IScenarioResultModel {

	Integer getId();
	Integer getScenarioId();
	String getDescription();
	String getTimeline();
	String getExecutor();
	org.joda.time.DateTime getCreatedDateTime();
	String getServerVersion();
	String getClientVersion();
	String getDevice();


}

/*       S.D.G.       */
