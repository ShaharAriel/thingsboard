package com.devicewise.tr50.api.response.log;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenLogList extends DwOpenGenericResponse{
	
	private int count;
	private ArrayList<LogEntry> result;
	
	public static class LogEntry{
		
		private String id;
		private String ts;
		private String thingId;
		private String corrId;
		private int level;
		private String msg;
		private int type;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTs() {
			return ts;
		}
		public void setTs(String ts) {
			this.ts = ts;
		}
		public String getThingId() {
			return thingId;
		}
		public void setThingId(String thingId) {
			this.thingId = thingId;
		}
		public String getCorrId() {
			return corrId;
		}
		public void setCorrId(String corrId) {
			this.corrId = corrId;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
	}

		public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<LogEntry> getResult() {
		return result;
	}

	public void setResult(ArrayList<LogEntry> result) {
		this.result = result;
	}

}
