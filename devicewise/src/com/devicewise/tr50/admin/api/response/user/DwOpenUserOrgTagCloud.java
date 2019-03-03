package com.devicewise.tr50.admin.api.response.user;

import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenUserOrgTagCloud extends DwOpenGenericResponse{
	
	private LinkedHashMap<String,Integer> result;

	public LinkedHashMap<String,Integer> getResult() {
		return result;
	}

	public void setResult(LinkedHashMap<String,Integer> result) {
		this.result = result;
	}

}
