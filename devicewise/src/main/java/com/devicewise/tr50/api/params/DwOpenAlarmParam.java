package com.devicewise.tr50.api.params;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.devicewise.tr50.json.DwJsonObjectWithKey;

@org.codehaus.jackson.annotate.JsonIgnoreProperties({"key"})
@JsonPropertyOrder({"viewTags","updateTags","states","name"})
public class DwOpenAlarmParam extends DwJsonObjectWithKey{

	private String name;
	private ArrayList<AlarmState> states;
	private String[] viewTags;
	private String[] updateTags;
	
	public static class AlarmState{
		
		private String name;
		private String color;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<AlarmState> getStates() {
		return states;
	}
	
	public void setStates(ArrayList<AlarmState> states) {
		this.states = states;
	}
	
	public String[] getViewTags() {
		return viewTags;
	}
	
	public void setViewTags(String[] viewTags) {
		this.viewTags = viewTags;
	}
	
	public String[] getUpdateTags() {
		return updateTags;
	}
	
	public void setUpdateTags(String[] updateTags) {
		this.updateTags = updateTags;
	}
}
