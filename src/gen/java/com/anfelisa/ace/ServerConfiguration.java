package com.anfelisa.ace;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerConfiguration {
	public static final String REPLAY = "REPLAY";
	public static final String REPLAY_EVENTS = "REPLAY_EVENTS";
	public static final String LIVE = "LIVE";
	public static final String DEV = "DEV";
	
	private String mode = DEV;

	@JsonProperty("mode")
	public String getMode() {
		return mode;
	}

	@JsonProperty("mode")
	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
