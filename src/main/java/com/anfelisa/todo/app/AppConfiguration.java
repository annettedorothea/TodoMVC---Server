package com.anfelisa.todo.app;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AppConfiguration extends Configuration {

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();

	private DataSourceFactory timelineDatabase = new DataSourceFactory();
	
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

	@JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.database = dataSourceFactory;
	}

	@JsonProperty("timelineDatabase")
	public DataSourceFactory getTimelineDataSourceFactory() {
		return timelineDatabase;
	}
	
	@JsonProperty("timelineDatabase")
	public void setTimelineDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.timelineDatabase = dataSourceFactory;
	}
	
}
