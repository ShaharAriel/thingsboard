package com.devicewise.tr50.api.response.thing;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenThingAttributeGet extends DwOpenGenericResponse{

	private String value;
	private String ts;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}

}
