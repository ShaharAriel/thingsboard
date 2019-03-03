package com.devicewise.tr50.api.response.diag;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

@JsonPropertyOrder({"result","success","errorcodes","jsonResponse","errormessages"})
public class DwOpenDiagThingStats extends DwOpenGenericResponse{

	private ArrayList<ThingStat> result;

	@JsonPropertyOrder({"connected","disconnected","id","key"})
	public static class ThingStat{
		
		private String id;
		private String key;
		private int connected;
		private int disconnected;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public int getConnected() {
			return connected;
		}
		public void setConnected(int connected) {
			this.connected = connected;
		}
		public int getDisconnected() {
			return disconnected;
		}
		public void setDisconnected(int disconnected) {
			this.disconnected = disconnected;
		}
	}

	public ArrayList<ThingStat> getResult() {
		return result;
	}

	public void setResult(ArrayList<ThingStat> result) {
		this.result = result;
	}

}
