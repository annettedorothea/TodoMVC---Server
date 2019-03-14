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
import com.anfelisa.todo.models.ITodoDoneHistoryModel;

import com.anfelisa.ace.AbstractData;
import com.anfelisa.ace.IDataContainer;

@SuppressWarnings("unused")
public abstract class AbstractTodoDoneHistoryData extends AbstractData implements ITodoDoneHistoryData {
	
	static final Logger LOG = LoggerFactory.getLogger(AbstractTodoDoneHistoryData.class);
	
	@NotNull
	private String id;
	
	@NotNull
	private String description;
	
	private org.joda.time.DateTime doneDateTime;
	

	public AbstractTodoDoneHistoryData(
		@JsonProperty("id") String id,
		@JsonProperty("description") String description,
		@JsonProperty("doneDateTime") org.joda.time.DateTime doneDateTime
,		@JsonProperty("uuid") String uuid
	) {
		super(uuid);
		this.id = id;
		this.description = description;
		this.doneDateTime = doneDateTime;
	}

	public AbstractTodoDoneHistoryData( String uuid ) {
		super(uuid);
	}

	@JsonProperty
		public String getId() {
			return this.id;
		}
	public void setId(String id) {
		this.id = id;
	}
	public ITodoDoneHistoryData withId(String id) {
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
	public ITodoDoneHistoryData withDescription(String description) {
		this.description = description;
		return this;
	}
	
	@JsonProperty
		public org.joda.time.DateTime getDoneDateTime() {
			return this.doneDateTime;
		}
	public void setDoneDateTime(org.joda.time.DateTime doneDateTime) {
		this.doneDateTime = doneDateTime;
	}
	public ITodoDoneHistoryData withDoneDateTime(org.joda.time.DateTime doneDateTime) {
		this.doneDateTime = doneDateTime;
		return this;
	}
	
	
	
	public List<String> equalsPrimitiveTypes(com.anfelisa.todo.models.ITodoDoneHistoryModel other) {
		List<String> differingAttributes = new ArrayList<String>();
		if (!(this.getId() == null && other.getId() == null) && !this.getId().equals(other.getId())) {
			differingAttributes.add("id: " + this.getId() + " " + other.getId());
		}
		if (!(this.getDescription() == null && other.getDescription() == null) && !this.getDescription().equals(other.getDescription())) {
			differingAttributes.add("description: " + this.getDescription() + " " + other.getDescription());
		}
		if (!(this.getDoneDateTime() == null && other.getDoneDateTime() == null) && !this.getDoneDateTime().equals(other.getDoneDateTime())) {
			differingAttributes.add("doneDateTime: " + this.getDoneDateTime() + " " + other.getDoneDateTime());
		}
		return differingAttributes;
	}
	
	
}




/******* S.D.G. *******/



