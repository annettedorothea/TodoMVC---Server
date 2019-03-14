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




package com.anfelisa.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import com.anfelisa.todo.models.ITodoModel;

import com.anfelisa.ace.AbstractData;
import com.anfelisa.ace.IDataContainer;

@SuppressWarnings("unused")
public abstract class AbstractTodoData extends AbstractData implements ITodoData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractTodoData.class);
	
	@NotNull
	private String id;
	
	@NotNull
	private String description;
	
	@NotNull
	private Boolean done = false;
	
	private org.joda.time.DateTime createdDateTime;
	
	private org.joda.time.DateTime updatedDateTime;
	

	public AbstractTodoData(
		@JsonProperty("id") String id,
		@JsonProperty("description") String description,
		@JsonProperty("done") Boolean done,
		@JsonProperty("createdDateTime") org.joda.time.DateTime createdDateTime,
		@JsonProperty("updatedDateTime") org.joda.time.DateTime updatedDateTime
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
		this.description = description;
		this.done = done;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
	}

	public AbstractTodoData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
		public String getId() {
			return this.id;
		}
	public void setId(String id) {
		this.id = id;
	}
	public ITodoData withId(String id) {
		this.id = id;
		return this;
	}
	
	@JsonProperty
		public String getDescription() {
			return this.description;
		}
	public void setDescription(String description) {
		this.description = description;
	}
	public ITodoData withDescription(String description) {
		this.description = description;
		return this;
	}
	
	@JsonProperty
		public Boolean getDone() {
			return this.done;
		}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public ITodoData withDone(Boolean done) {
		this.done = done;
		return this;
	}
	
	@JsonProperty
		public org.joda.time.DateTime getCreatedDateTime() {
			return this.createdDateTime;
		}
	public void setCreatedDateTime(org.joda.time.DateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public ITodoData withCreatedDateTime(org.joda.time.DateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
		return this;
	}
	
	@JsonProperty
		public org.joda.time.DateTime getUpdatedDateTime() {
			return this.updatedDateTime;
		}
	public void setUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public ITodoData withUpdatedDateTime(org.joda.time.DateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
		return this;
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



