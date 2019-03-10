package com.devicewise.tr50.api.response.session;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenSessionList extends DwOpenGenericResponse{
	
	private int count;
	private ArrayList<DwOpenSessionFind> result;
	
	public ArrayList<DwOpenSessionFind> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenSessionFind> result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
