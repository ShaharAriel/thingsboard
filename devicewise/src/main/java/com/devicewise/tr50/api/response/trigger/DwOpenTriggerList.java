package com.devicewise.tr50.api.response.trigger;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenTriggerList extends DwOpenGenericResponse{
	
	private ArrayList<DwOpenTriggerFind> result;

	public ArrayList<DwOpenTriggerFind> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenTriggerFind> result) {
		this.result = result;
	}
}
