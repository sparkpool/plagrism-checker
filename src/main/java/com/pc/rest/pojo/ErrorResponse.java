package com.pc.rest.pojo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ErrorResponse {

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("errors")
	private List<ErrorPojo> errors = new ArrayList<ErrorPojo>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorPojo> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorPojo> errors) {
		this.errors = errors;
	}
	
}
