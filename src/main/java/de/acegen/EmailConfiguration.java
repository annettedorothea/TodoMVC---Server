package de.acegen;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailConfiguration {
	
	@JsonProperty
	private String host;

	@JsonProperty
	private int port;
	
	@JsonProperty
	private String user;

	@JsonProperty
	private String password;

	@JsonProperty
	private boolean tls;
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public boolean isTls() {
		return tls;
	}
	
}
