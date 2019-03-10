package com.devicewise.tr50.admin.api.response.org;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenOrgList extends DwOpenGenericResponse{
	
	private ArrayList<DwOpenOrgFind> result;

	public ArrayList<DwOpenOrgFind> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenOrgFind> result) {
		this.result = result;
	}

}
