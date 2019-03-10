package com.devicewise.tr50.api.response.file;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenFileTagCloud extends DwOpenGenericResponse{
	
	private ArrayList<LinkedHashMap<String,Integer>> result;

	public ArrayList<LinkedHashMap<String,Integer>> getResult() {
		return result;
	}

	public void setResult(ArrayList<LinkedHashMap<String,Integer>> result) {
		this.result = result;
	}
}
