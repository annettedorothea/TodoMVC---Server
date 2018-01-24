package com.anfelisa.ace.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class ScenarioResultModel implements IScenarioResultModel {

	@NotNull
	private Integer id;
	
	@NotNull
	private Integer scenarioId;
	
	@NotNull
	private String description;
	
	@NotNull
	private String timeline;
	
	@NotNull
	private String executor;
	
	private org.joda.time.DateTime createdDateTime;
	
	@NotNull
	private String serverVersion;
	
	@NotNull
	private String clientVersion;
	
	@NotNull
	private String device;
	
	@NotNull
	private Boolean result;

	@NotNull
	private Boolean e2e;

	public ScenarioResultModel(
		@JsonProperty("id") Integer id,
		@JsonProperty("scenarioId") Integer scenarioId,
		@JsonProperty("description") String description,
		@JsonProperty("timeline") String timeline,
		@JsonProperty("executor") String executor,
		@JsonProperty("createdDateTime") org.joda.time.DateTime createdDateTime,
		@JsonProperty("serverVersion") String serverVersion,
		@JsonProperty("clientVersion") String clientVersion,
		@JsonProperty("device") String device,
		@JsonProperty("result") Boolean result,
		@JsonProperty("e2e") Boolean e2e
	) {
		this.id = id;
		this.scenarioId = scenarioId;
		this.description = description;
		this.timeline = timeline;
		this.executor = executor;
		this.createdDateTime = createdDateTime;
		this.serverVersion = serverVersion;
		this.clientVersion = clientVersion;
		this.device = device;
		this.result = result;
		this.e2e = e2e;
	}

	@JsonProperty
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty
	public Integer getScenarioId() {
		return this.scenarioId;
	}
	public void setScenarioId(Integer scenarioId) {
		this.scenarioId = scenarioId;
	}
	
	@JsonProperty
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
	public String getTimeline() {
		return this.timeline;
	}
	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}
	
	@JsonProperty
	public String getExecutor() {
		return this.executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getCreatedDateTime() {
		return this.createdDateTime;
	}
	public void setCreatedDateTime(org.joda.time.DateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	@JsonProperty
	public String getServerVersion() {
		return this.serverVersion;
	}
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}
	
	@JsonProperty
	public String getClientVersion() {
		return this.clientVersion;
	}
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
	
	@JsonProperty
	public String getDevice() {
		return this.device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
	@JsonProperty
	public Boolean getResult() {
		return this.result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	
	@JsonProperty
	public Boolean getE2E() {
		return this.e2e;
	}
	public void setE2E(Boolean e2e) {
		this.e2e = e2e;
	}
	
}

/*       S.D.G.       */

