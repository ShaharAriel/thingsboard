package com.devicewise.tr50.api.response.mailbox;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenMailboxList extends DwOpenGenericResponse{

	private ArrayList<DwOpenMailboxItem> result;
	private int count;

	public ArrayList<DwOpenMailboxItem> getResult() {
		return result;
	}

	public void setResult(ArrayList<DwOpenMailboxItem> result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
