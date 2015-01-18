package com.pc.rest.pojo;

import java.util.ArrayList;
import java.util.List;

import com.pc.rest.core.JSONObject;

public class GenericResponse extends JSONObject{

	@Override
	protected List<String> getKeys() {
		List<String> keys = new ArrayList<String>();
		return keys;
	}

	@Override
	protected List<Object> getValues() {
		List<Object> values = new ArrayList<Object>();
		return values;
	}

}
