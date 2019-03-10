package com.devicewise.tr50.api.response.property;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenPropertyHistory extends DwOpenGenericResponse{
	
	protected ArrayList<values> values;
	
	public static class values{
		
		private double value;
		private String ts;

		public String getTs() {
			return ts;
		}
		public void setTs(String ts) {
			this.ts = ts;
		}
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}
	}

	public ArrayList<values> getValues() {
		return values;
	}

	public void setValues(ArrayList<values> values) {
		this.values = values;
	}

}
