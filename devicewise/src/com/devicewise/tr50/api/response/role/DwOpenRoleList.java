package com.devicewise.tr50.api.response.role;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenRoleList extends DwOpenGenericResponse{
	
	private ArrayList<DwOpenRoleFind> result;

	public ArrayList<DwOpenRoleFind> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenRoleFind> result) {
		this.result = result;
	}
}
