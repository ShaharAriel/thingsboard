package com.devicewise.tr50.api.response.alarm;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenAlarmHistory extends DwOpenGenericResponse{
	
	private ArrayList<values> values;
	private String[] timestamps;
	private int[] states;
	private String[] messages;
	
	public static class values{
		
		private String ts;
		private String msg;
		private int state;
		private int duration;

		public String getTs() {
			return ts;
		}
		public void setTs(String ts) {
			this.ts = ts;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public int getDuration() {
			return duration;
		}
		public void setDuration(int duration) {
			this.duration = duration;
		}
	}

	public ArrayList<values> getValues() {
		return values;
	}

	public void setValues(ArrayList<values> values) {
		this.values = values;
	}

	public String[] getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(String[] timestamps) {
		this.timestamps = timestamps;
	}

	public int[] getStates() {
		return states;
	}

	public void setStates(int[] states) {
		this.states = states;
	}

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

}
