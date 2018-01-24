package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class ScenarioModel implements IScenarioModel {

	@NotNull
	private Integer id;
	
	@NotNull
	private String description;
	
	@NotNull
	private String timeline;
	
	@NotNull
	private String creator;
	
	private org.joda.time.DateTime createdDateTime;
	
	private org.joda.time.DateTime updatedDateTime;
	
	@NotNull
	private String serverVersion;
	
	@NotNull
	private String clientVersion;
	
	@NotNull
	private String device;
	


	public ScenarioModel(
		@JsonProperty("id") Integer id,
		@JsonProperty("description") String description,
		@JsonProperty("timeline") String timeline,
		@JsonProperty("creator") String creator,
		@JsonProperty("createdDateTime") org.joda.time.DateTime createdDateTime,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime,
		@JsonProperty("serverVersion") String serverVersion,
		@JsonProperty("clientVersion") String clientVersion,
		@JsonProperty("device") String device
	) {
		this.id = id;
		this.description = description;
		this.timeline = timeline;
		this.creator = creator;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
		this.serverVersion = serverVersion;
		this.clientVersion = clientVersion;
		this.device = device;
	}

	@JsonProperty
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getCreator() {
		return this.creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getCreatedDateTime() {
		return this.createdDateTime;
	}
	public void setCreatedDateTime(org.joda.time.DateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getUpdatedDateTime() {
		return this.updatedDateTime;
	}
	public void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
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
	

		
}

/*       S.D.G.       */
