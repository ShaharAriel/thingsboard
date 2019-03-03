package com.devicewise.tr50.api.response.usage;

import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenUsageHistory extends DwOpenGenericResponse{

	private LinkedHashMap<String,Integer> counters;
	private int total;
	private String[] ts;
	
	public LinkedHashMap<String,Integer> getCounters() {
		return counters;
	}
	public void setCounters(LinkedHashMap<String,Integer> counters) {
		this.counters = counters;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String[] getTs() {
		return ts;
	}
	public void setTs(String[] ts) {
		this.ts = ts;
	}
	
}
