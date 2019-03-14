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
public class TodoModel implements ITodoModel {

	@NotNull
	private String id;
	
	@NotNull
	private String description;
	
	@NotNull
	private Boolean done = false;
	
	private org.joda.time.DateTime createdDateTime;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public TodoModel() {
	}

	public TodoModel(
		@JsonProperty("id") String id,
		@JsonProperty("description") String description,
		@JsonProperty("done") Boolean done,
		@JsonProperty("createdDateTime") org.joda.time.DateTime createdDateTime,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
	) {
		this.id = id;
		this.description = description;
		this.done = done;
		this.createdDateTime = createdDateTime;
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
		public String getDescription() {
			return this.description;
		}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty
		public Boolean getDone() {
			return this.done;
		}
	public void setDone(Boolean done) {
		this.done = done;
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
	
	
	public List<String> equalsPrimitiveTypes(com.anfelisa.todo.models.ITodoModel other) {
		List<String> differingAttributes = new ArrayList<String>();
		if (!(this.getId() == null && other.getId() == null) && !this.getId().equals(other.getId())) {
			differingAttributes.add("id: " + this.getId() + " " + other.getId());
		}
		if (!(this.getDescription() == null && other.getDescription() == null) && !this.getDescription().equals(other.getDescription())) {
			differingAttributes.add("description: " + this.getDescription() + " " + other.getDescription());
		}
		if (!(this.getDone() == null && other.getDone() == null) && !this.getDone().equals(other.getDone())) {
			differingAttributes.add("done: " + this.getDone() + " " + other.getDone());
		}
		if (!(this.getCreatedDateTime() == null && other.getCreatedDateTime() == null) && !this.getCreatedDateTime().equals(other.getCreatedDateTime())) {
			differingAttributes.add("createdDateTime: " + this.getCreatedDateTime() + " " + other.getCreatedDateTime());
		}
		if (!(this.getUpdatedDateTime() == null && other.getUpdatedDateTime() == null) && !this.getUpdatedDateTime().equals(other.getUpdatedDateTime())) {
			differingAttributes.add("updatedDateTime: " + this.getUpdatedDateTime() + " " + other.getUpdatedDateTime());
		}
		return differingAttributes;
	}
	
}




/******* S.D.G. *******/



