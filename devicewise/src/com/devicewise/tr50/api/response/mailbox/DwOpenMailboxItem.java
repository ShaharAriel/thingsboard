package com.devicewise.tr50.api.response.mailbox;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenMailboxItem extends DwOpenGenericResponse{
	
	private String id;
	private String thingKey;
	private String thingId;
	private String sessionId;
	private String from;
	private String command;
	private String status;
	private String updated;
	private String expires;
	private String errorMessage;
	private String errorCode;
	private ArrayList<HistoryEntry> history;
	private LinkedHashMap<String,Object> params;
	
	public static class HistoryEntry{
		
		private String ts;
		private String status;
		private String message;
		
		public String getTs() {
			return ts;
		}
		public void setTs(String ts) {
			this.ts = ts;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThingKey() {
		return thingKey;
	}

	public void setThingKey(String thingKey) {
		this.thingKey = thingKey;
	}

	public String getThingId() {
		return thingId;
	}

	public void setThingId(String thingId) {
		this.thingId = thingId;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public ArrayList<HistoryEntry> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<HistoryEntry> history) {
		this.history = history;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public LinkedHashMap<String,Object> getParams() {
		return params;
	}

	public void setParams(LinkedHashMap<String,Object> params) {
		this.params = params;
	}

}
