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




package de.acegen;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractData implements IDataContainer {

	private String uuid;
	
	private String outcome;
	
	private LocalDateTime systemTime;
	
	public AbstractData( String uuid ) {
		this.uuid = uuid;
	}

	@JsonProperty
	public String getUuid() {
		return this.uuid;
	}

	@JsonProperty
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@JsonProperty
	public LocalDateTime getSystemTime() {
		return systemTime;
	}

	@JsonProperty
	public void setSystemTime(LocalDateTime systemTime) {
		this.systemTime = systemTime;
	}

	@JsonProperty
	public String getOutcome() {
		return outcome;
	}

	@JsonProperty
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
}




/******* S.D.G. *******/



