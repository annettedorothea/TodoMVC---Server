package de.acegen;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomAppConfiguration extends AppConfiguration {

	@Valid
	@NotNull
	@JsonProperty
	private EmailConfiguration email;
	
	public EmailConfiguration getEmail() {
		return email;
	}

}
