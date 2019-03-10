package com.devicewise.tr50.api.response.thingdef;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenThingDefList extends DwOpenGenericResponse{
	
	private ArrayList<DwOpenThingDefFind> result;

	public ArrayList<DwOpenThingDefFind> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenThingDefFind> result) {
		this.result = result;
	}
}
