package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TodoIdModel implements ITodoIdModel {

	@NotNull
	private String id;
	

	public TodoIdModel() {
	}

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
	
	
	public List<String> equalsPrimitiveTypes(ITodoIdModel other) {
		List<String> differingAttributes = new ArrayList<String>();
		if (!(this.getId() == null && other.getId() == null) && !this.getId().equals(other.getId())) {
			differingAttributes.add("id: " + this.getId() + " " + other.getId());
		}
		return differingAttributes;
	}
	
}

/*       S.D.G.       */
