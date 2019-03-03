package com.devicewise.tr50.admin.api.response.user;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenUserList extends DwOpenGenericResponse{
	
	private ArrayList<DwOpenUserFind> result;

	public ArrayList<DwOpenUserFind> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenUserFind> result) {
		this.result = result;
	}

}
