package com.anfelisa.todo.app.scenario;

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
	private String data;
	
	private org.joda.time.DateTime createdDateTime;
	


	public ScenarioModel(
		@JsonProperty("id") Integer id,
		@JsonProperty("description") String description,
		@JsonProperty("data") String data,
		@JsonProperty("createdDateTime") org.joda.time.DateTime createdDateTime
	) {
		this.id = id;
		this.description = description;
		this.data = data;
		this.createdDateTime = createdDateTime;
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
	public String getData() {
		return this.data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@JsonProperty
	public org.joda.time.DateTime getCreatedDateTime() {
		return this.createdDateTime;
	}
	public void setCreatedDateTime(org.joda.time.DateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	

		
}

/*       S.D.G.       */
