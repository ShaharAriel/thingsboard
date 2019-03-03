package com.devicewise.tr50.api.response.diag;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenDiagMongoStats extends DwOpenGenericResponse{
	
	private String set;
	private String date;
	private String myState;
	private ArrayList<String> members;
	
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMyState() {
		return myState;
	}
	public void setMyState(String myState) {
		this.myState = myState;
	}
	public ArrayList<String> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}

}
