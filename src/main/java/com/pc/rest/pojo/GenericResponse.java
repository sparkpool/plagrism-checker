package com.pc.rest.pojo;

import java.util.ArrayList;
import java.util.List;

import com.pc.rest.core.JSONObject;

public class GenericResponse extends JSONObject{

	private static final long serialVersionUID = 2561605336985002359L;

	private List<String> messages = new ArrayList<String>();
	
	private String error;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void addMessage(String message){
		messages.add(message);
	}
	
	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	@Override
	protected List<String> getKeys() {
		List<String> keys = new ArrayList<String>();
		keys.add("msgs");
		keys.add("error");
		return keys;
	}

	@Override
	protected List<Object> getValues() {
		List<Object> values = new ArrayList<Object>();
		values.add(messages);
		values.add(error);
		return values;
	}

}
