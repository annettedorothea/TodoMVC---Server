/* 
 * Copyright (c) 2019, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */




package com.anfelisa.todo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TodoToggleModel implements ITodoToggleModel {

	@NotNull
	private String id;
	
	@NotNull
	private Boolean done = false;
	
	private com.anfelisa.todo.models.ITodoModel todoToBeToggled;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public TodoToggleModel() {
	}

	public TodoToggleModel(
		@JsonProperty("id") String id,
		@JsonProperty("done") Boolean done,
		@JsonProperty("todoToBeToggled") com.anfelisa.todo.models.ITodoModel todoToBeToggled,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
	) {
		this.id = id;
		this.done = done;
		this.todoToBeToggled = todoToBeToggled;
		this.updatedDateTime = updatedDateTime;
	}

	@JsonProperty
		public String getId() {
			return this.id;
		}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty
		public Boolean getDone() {
			return this.done;
		}
	public void setDone(Boolean done) {
		this.done = done;
	}
	
	@JsonProperty
		public com.anfelisa.todo.models.ITodoModel getTodoToBeToggled() {
			return this.todoToBeToggled;
		}
	public void setTodoToBeToggled(com.anfelisa.todo.models.ITodoModel todoToBeToggled) {
		this.todoToBeToggled = todoToBeToggled;
	}
	
	@JsonProperty
		public org.joda.time.DateTime getUpdatedDateTime() {
			return this.updatedDateTime;
		}
	public void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	
	
	public List<String> equalsPrimitiveTypes(com.anfelisa.todo.models.ITodoToggleModel other) {
		List<String> differingAttributes = new ArrayList<String>();
		if (!(this.getId() == null && other.getId() == null) && !this.getId().equals(other.getId())) {
			differingAttributes.add("id: " + this.getId() + " " + other.getId());
		}
		if (!(this.getDone() == null && other.getDone() == null) && !this.getDone().equals(other.getDone())) {
			differingAttributes.add("done: " + this.getDone() + " " + other.getDone());
		}
		if (!(this.getUpdatedDateTime() == null && other.getUpdatedDateTime() == null) && !this.getUpdatedDateTime().equals(other.getUpdatedDateTime())) {
			differingAttributes.add("updatedDateTime: " + this.getUpdatedDateTime() + " " + other.getUpdatedDateTime());
		}
		return differingAttributes;
	}
	
}




/******* S.D.G. *******/



