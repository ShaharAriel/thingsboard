package com.devicewise.tr50.api.response.tunnel;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenTunnelConnHistory extends DwOpenGenericResponse{

	private ArrayList<DwOpenTunnelConn> connections;
	private int count;

	public static class DwOpenTunnelConn{
		
		private String thingId;
		private String tunId;
		private int connId;
		private String state;
		private String openedTs;
		private String closedTs;
		private int bytesSent;
		private int bytesRecv;
		private double mbpsSent;
		private double mbpsRecv;
		
		public String getThingId() {
			return thingId;
		}
		public void setThingId(String thingId) {
			this.thingId = thingId;
		}
		public String getTunId() {
			return tunId;
		}
		public void setTunId(String tunId) {
			this.tunId = tunId;
		}
		public int getConnId() {
			return connId;
		}
		public void setConnId(int connId) {
			this.connId = connId;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getOpenedTs() {
			return openedTs;
		}
		public void setOpenedTs(String openedTs) {
			this.openedTs = openedTs;
		}
		public int getBytesSent() {
			return bytesSent;
		}
		public void setBytesSent(int bytesSent) {
			this.bytesSent = bytesSent;
		}
		public String getClosedTs() {
			return closedTs;
		}
		public void setClosedTs(String closedTs) {
			this.closedTs = closedTs;
		}
		public int getBytesRecv() {
			return bytesRecv;
		}
		public void setBytesRecv(int bytesRecv) {
			this.bytesRecv = bytesRecv;
		}
		public double getMbpsSent() {
			return mbpsSent;
		}
		public void setMbpsSent(double mbpsSent) {
			this.mbpsSent = mbpsSent;
		}
		public double getMbpsRecv() {
			return mbpsRecv;
		}
		public void setMbpsRecv(double mbpsRecv) {
			this.mbpsRecv = mbpsRecv;
		}
	}

	public ArrayList<DwOpenTunnelConn> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<DwOpenTunnelConn> connections) {
		this.connections = connections;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
