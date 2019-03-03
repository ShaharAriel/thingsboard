package com.devicewise.tr50.api.params;

import com.devicewise.tr50.json.DwJsonObjectWithKey;

@org.codehaus.jackson.annotate.JsonIgnoreProperties({"key"})
public class DwOpenThingAttribute extends DwJsonObjectWithKey {

	private String name;
	private String value;
	private String ts;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
