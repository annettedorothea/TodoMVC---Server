package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("all")
public class TodoIdModel implements ITodoIdModel {

	@NotNull
	private Integer id;
	

	public TodoIdModel(
		@JsonProperty("id") Integer id
	) {
		this.id = id;
	}

	@JsonProperty
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

}

/*       S.D.G.       */
