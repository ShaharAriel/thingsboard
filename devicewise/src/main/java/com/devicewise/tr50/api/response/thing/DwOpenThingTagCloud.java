package com.devicewise.tr50.api.response.thing;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenThingTagCloud extends DwOpenGenericResponse{
	
	private ArrayList<LinkedHashMap<String,Integer>> result;
	private ArrayList<LinkedHashMap<String,Integer>> special;
	
	public ArrayList<LinkedHashMap<String,Integer>> getResult() {
		return result;
	}
	public void setResult(ArrayList<LinkedHashMap<String,Integer>> result) {
		this.result = result;
	}
	public ArrayList<LinkedHashMap<String,Integer>> getSpecial() {
		return special;
	}
	public void setSpecial(ArrayList<LinkedHashMap<String,Integer>> special) {
		this.special = special;
	}


}
