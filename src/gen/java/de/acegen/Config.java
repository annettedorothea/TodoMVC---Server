	/********************************************************************************
	 * generated by de.acegen 1.5.5
	 ********************************************************************************/
	
	
	
	
	package de.acegen;
	
	import com.fasterxml.jackson.annotation.JsonProperty;

	public class Config {
		public static final String LIVE = "LIVE";
		public static final String DEV = "DEV";
	
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
	
	
	
