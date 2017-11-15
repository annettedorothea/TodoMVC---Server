package com.anfelisa.todo.app.scenario;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=ScenarioModel.class)
public interface IScenarioModel {

	Integer getId();
	String getDescription();
	String getData();
	org.joda.time.DateTime getCreatedDateTime();


}

/*       S.D.G.       */
