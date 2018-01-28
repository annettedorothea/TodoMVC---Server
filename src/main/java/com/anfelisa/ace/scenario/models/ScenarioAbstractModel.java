package com.anfelisa.ace.scenario.models;

import org.joda.time.DateTime;

import com.anfelisa.ace.scenario.ScenarioModel;

public class ScenarioAbstractModel extends ScenarioModel implements IScenarioAbstractModel {

	private Boolean lastScenarioResult;

	public ScenarioAbstractModel(Integer id, String description, String timeline, String creator, DateTime createdDateTime,
			DateTime updatedDateTime, String serverVersion, String clientVersion, String device, Boolean lastScenarioResult) {
		super(id, description, timeline, creator, createdDateTime, updatedDateTime, serverVersion, clientVersion,
				device);
		this.lastScenarioResult = lastScenarioResult;
	}

	public void setLastScenarioResult(Boolean lastScenarioResult) {
		this.lastScenarioResult = lastScenarioResult;
	}

	@Override
	public Boolean getLastScenarioResult() {
		return lastScenarioResult;
	}

}
