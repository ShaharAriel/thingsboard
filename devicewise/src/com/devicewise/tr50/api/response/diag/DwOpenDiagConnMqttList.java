package com.devicewise.tr50.api.response.diag;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenDiagConnMqttList extends DwOpenGenericResponse{
	
	private int count;
	private ArrayList<MqttConn> result;
	
	public static class MqttConn{
		
		private String clientId;
		private String clientAddr;
		private String connType;
		private int msgsIn;
		private int msgsOut;
		private String lastConn;
		private String lastActivity;
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getClientAddr() {
			return clientAddr;
		}
		public void setClientAddr(String clientAddr) {
			this.clientAddr = clientAddr;
		}
		public String getConnType() {
			return connType;
		}
		public void setConnType(String connType) {
			this.connType = connType;
		}
		public int getMsgsIn() {
			return msgsIn;
		}
		public void setMsgsIn(int msgsIn) {
			this.msgsIn = msgsIn;
		}
		public int getMsgsOut() {
			return msgsOut;
		}
		public void setMsgsOut(int msgsOut) {
			this.msgsOut = msgsOut;
		}
		public String getLastConn() {
			return lastConn;
		}
		public void setLastConn(String lastConn) {
			this.lastConn = lastConn;
		}
		public String getLastActivity() {
			return lastActivity;
		}
		public void setLastActivity(String lastActivity) {
			this.lastActivity = lastActivity;
		}

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<MqttConn> getResult() {
		return result;
	}

	public void setResult(ArrayList<MqttConn> result) {
		this.result = result;
	}
}
