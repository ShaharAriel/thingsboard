package com.devicewise.tr50.api.response.property;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenPropertyHistorySplit extends DwOpenGenericResponse{
	
	protected double[] values;
	protected String[] timestamps;
	
	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}

	public String[] getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(String[] timestamps) {
		this.timestamps = timestamps;
	}

}
