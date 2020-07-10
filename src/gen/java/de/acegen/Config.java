	/* 
	 * Copyright (c) 2020, Annette Pohl, Koblenz, Germany
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
	 *
	 * generated with de.acegen 0.9.6
	 *
	 */
	
	
	
	
	package de.acegen;
	
	import com.fasterxml.jackson.annotation.JsonProperty;

	public class Config {
		public static final String REPLAY = "REPLAY";
		public static final String LIVE = "LIVE";
		public static final String DEV = "DEV";
		public static final String TEST = "TEST";
	
		public static final String ALWAYS = "ALWAYS";
		public static final String ON_ERROR = "ON_ERROR";
		public static final String NEVER = "NEVER";
		
		private String mode = DEV;
		
		private String writeTimeline = ON_ERROR;
	
		@JsonProperty("mode")
		public String getMode() {
			return mode;
		}
	
		@JsonProperty("mode")
		public void setMode(String mode) {
			this.mode = mode;
		}
	
		@JsonProperty("writeTimeline")
		public String getWriteTimeline() {
			return writeTimeline;
		}
	
		@JsonProperty("writeTimeline")
		public void setWriteTimeline(String writeTimeline) {
			this.writeTimeline = writeTimeline;
		}
		
		public boolean writeTimeline() {
			return !NEVER.equals(writeTimeline);
		}
		
		public boolean writeError() {
			return ON_ERROR.equals(writeTimeline) || ALWAYS.equals(writeTimeline);
		}

	}
	
	
	
	/******* S.D.G. *******/
	
	
	
