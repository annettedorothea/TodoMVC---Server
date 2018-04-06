package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class TodoIdModel implements ITodoIdModel {

	@NotNull
	private String id;
	

	public TodoIdModel(
		@JsonProperty("id") String id
	) {
		this.id = id;
	}

	@JsonProperty
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}

/*       S.D.G.       */
