package com.anfelisa.todo.app.bug;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class BugModel implements IBugModel {

	@NotNull
	private Integer id;
	
	@NotNull
	private String description;
	
	@NotNull
	private String data;
	
	@NotNull
	private String reporter;
	
	@NotNull
	private Boolean resolved;
	
	private org.joda.time.DateTime createdDateTime;
	
	private org.joda.time.DateTime updatedDateTime;
	


	public BugModel(
		@JsonProperty("id") Integer id,
		@JsonProperty("description") String description,
		@JsonProperty("data") String data,
		@JsonProperty("reporter") String reporter,
		@JsonProperty("resolved") Boolean resolved,
		@JsonProperty("createdDateTime") org.joda.time.DateTime createdDateTime,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
	) {
		this.id = id;
		this.description = description;
		this.data = data;
		this.reporter = reporter;
		this.resolved = resolved;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
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
	public String getReporter() {
		return this.reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	
	@JsonProperty
	public Boolean getResolved() {
		return this.resolved;
	}
	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
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
	

		
}

/*       S.D.G.       */
