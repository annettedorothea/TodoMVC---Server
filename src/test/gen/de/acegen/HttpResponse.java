/********************************************************************************
 * generated by de.acegen 1.3.0
 ********************************************************************************/




package de.acegen;

public class HttpResponse<T> {

	private T entity;
	
	private String statusMessage;
	
	private int statusCode;
	
	private long duration;

	public HttpResponse(T entity, String statusMessage, int statusCode, long duration) {
		super();
		this.entity = entity;
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
		this.duration = duration;
	}

	public T getEntity() {
		return entity;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public long getDuration() {
		return duration;
	}
	
}





/******* S.D.G. *******/



