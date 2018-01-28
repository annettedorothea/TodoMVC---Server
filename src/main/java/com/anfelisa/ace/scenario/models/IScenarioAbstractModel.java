package com.anfelisa.ace.scenario.models;

import com.anfelisa.ace.scenario.IScenarioModel;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=ScenarioAbstractModel.class)
public interface IScenarioAbstractModel extends IScenarioModel {
	Boolean getLastScenarioResult();
}
