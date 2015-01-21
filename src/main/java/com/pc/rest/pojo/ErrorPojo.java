package com.pc.rest.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

public class ErrorPojo {

	@JsonProperty("domain")
	private String domain;
	
	@JsonProperty("reason")
	private String reason;
	
	@JsonProperty("message")
	private String message;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
