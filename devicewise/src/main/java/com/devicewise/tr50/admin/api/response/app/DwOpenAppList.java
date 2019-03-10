package com.devicewise.tr50.admin.api.response.app;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenAppList extends DwOpenGenericResponse{
	
	private ArrayList<DwOpenAppFind> result;

	public ArrayList<DwOpenAppFind> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenAppFind> result) {
		this.result = result;
	}

}
