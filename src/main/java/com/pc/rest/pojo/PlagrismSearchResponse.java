package com.pc.rest.pojo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PlagrismSearchResponse extends GenericResponse{

	@JsonProperty("items")
	private List<Item> items = new ArrayList<Item>();

	@JsonProperty("error")
	private ErrorResponse errorResponse;
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	@Override
	protected List<String> getKeys() {
		List<String> keys = super.getKeys();
		keys.add("items");
		return keys;
	}

	@Override
	protected List<Object> getValues() {
		List<Object> values = super.getValues();
		values.add(items);
		return values;
	}

	@Override
	public String toString() {
		return "PlagrismSearchResponse [items=" + items + "]";
	}
	
}
