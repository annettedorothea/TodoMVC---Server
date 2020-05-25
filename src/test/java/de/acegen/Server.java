package de.acegen;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Server {

	private String rootPath;

	private Connectors[] applicationConnectors;

	@JsonProperty("rootPath")
	public String getRootPath() {
		return rootPath;
	}

	@JsonProperty("rootPath")
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	@JsonProperty("applicationConnectors")
	public Connectors[] getApplicationConnectors() {
		return applicationConnectors;
	}

	@JsonProperty("applicationConnectors")
	public void setApplicationConnectors(Connectors[] applicationConnectors) {
		this.applicationConnectors = applicationConnectors;
	}
	
	

}
