package com.devicewise.tr50.api.params;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({"value","key","corrId","ts"})
public class DwOpenPropertyParam {
	
	private String ts;
	private String key;
	private String corrId;
	private double value;

	public String getTs() {
		return ts;
	}
	public void setTs(String timestamp) {
		this.ts = timestamp;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCorrId() {
		return corrId;
	}
	public void setCorrId(String corrId) {
		this.corrId = corrId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

}
