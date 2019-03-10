package com.devicewise.tr50.api.response.tunnel;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenTunnelHistory extends DwOpenGenericResponse{

	private ArrayList<DwOpenTunnelDef> tunnels;
	private int count;

	public static class DwOpenTunnelDef{
		
		private String id;
		private String thingId;
		private String tunId;
		private String connId;
		private String routerKey;
		private String name;
		private String key;
		private int port;
		private String state;
		private String reason;
		private int idleTimeout;
		private String openedBy;
		private String openedTs;
		private String updatedTs;
		private String closedTs;
		private String idleTs;
		
		public String getThingId() {
			return thingId;
		}
		public void setThingId(String thingId) {
			this.thingId = thingId;
		}
		public String getRouterKey() {
			return routerKey;
		}
		public void setRouterKey(String tunId) {
			this.routerKey = tunId;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int connId) {
			this.port = connId;
		}
		public String getName() {
			return name;
		}
		public void setName(String state) {
			this.name = state;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String openedTs) {
			this.key = openedTs;
		}
		public int getIdleTimeout() {
			return idleTimeout;
		}
		public void setIdleTimeout(int bytesSent) {
			this.idleTimeout = bytesSent;
		}
		public String getState() {
			return state;
		}
		public void setState(String closedTs) {
			this.state = closedTs;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getOpenedBy() {
			return openedBy;
		}
		public void setOpenedBy(String openedBy) {
			this.openedBy = openedBy;
		}
		public String getOpenedTs() {
			return openedTs;
		}
		public void setOpenedTs(String openedTs) {
			this.openedTs = openedTs;
		}
		public String getUpdatedTs() {
			return updatedTs;
		}
		public void setUpdatedTs(String updatedTs) {
			this.updatedTs = updatedTs;
		}
		public String getTunId() {
			return tunId;
		}
		public void setTunId(String tunId) {
			this.tunId = tunId;
		}
		public String getConnId() {
			return connId;
		}
		public void setConnId(String connId) {
			this.connId = connId;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getClosedTs() {
			return closedTs;
		}
		public void setClosedTs(String closedTs) {
			this.closedTs = closedTs;
		}
		public String getIdleTs() {
			return idleTs;
		}
		public void setIdleTs(String idleTs) {
			this.idleTs = idleTs;
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<DwOpenTunnelDef> getTunnels() {
		return tunnels;
	}

	public void setTunnels(ArrayList<DwOpenTunnelDef> tunnels) {
		this.tunnels = tunnels;
	}
	
}
