package com.devicewise.tr50.api.response.thing;

import java.util.ArrayList;

import com.devicewise.tr50.api.params.DwOpenGatewayParam;
import com.devicewise.tr50.api.params.DwOpenLocationParam;
import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenThingList extends DwOpenGenericResponse{
	
	private int count;
	private ArrayList<result> result;
	private String[] fields;

	public static class result{
		private String id;
		private String key;
		private String defKey;
		private String name;
		private String type;
		private boolean connected;
		private String connectedId;
		private String connectedKey;
		private String proto;
		private String remoteAddr;
		private String[] locWithin;
		private String locUpdated;
		private String lastSeen;
		private DwOpenLocationParam loc;
		private DwOpenGatewayParam gateway;
		
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean isConnected() {
			return connected;
		}
		public void setConnected(boolean connected) {
			this.connected = connected;
		}
		public String getProto() {
			return proto;
		}
		public void setProto(String proto) {
			this.proto = proto;
		}
		public String getRemoteAddr() {
			return remoteAddr;
		}
		public void setRemoteAddr(String remoteAddr) {
			this.remoteAddr = remoteAddr;
		}
		public String getLocUpdated() {
			return locUpdated;
		}
		public void setLocUpdated(String locUpdated) {
			this.locUpdated = locUpdated;
		}
		public String getLastSeen() {
			return lastSeen;
		}
		public void setLastSeen(String lastSeen) {
			this.lastSeen = lastSeen;
		}
		public String getConnectedId() {
			return connectedId;
		}
		public void setConnectedId(String connectedId) {
			this.connectedId = connectedId;
		}
		public String getConnectedKey() {
			return connectedKey;
		}
		public void setConnectedKey(String connectedKey) {
			this.connectedKey = connectedKey;
		}
		public DwOpenLocationParam getLoc() {
			return loc;
		}
		public void setLoc(DwOpenLocationParam loc) {
			this.loc = loc;
		}
		public DwOpenGatewayParam getGateway() {
			return gateway;
		}
		public void setGateway(DwOpenGatewayParam gateway) {
			this.gateway = gateway;
		}
		public String[] getLocWithin() {
			return locWithin;
		}
		public void setLocWithin(String[] locWithin) {
			this.locWithin = locWithin;
		}
		public String getDefKey() {
			return defKey;
		}
		public void setDefKey(String defKey) {
			this.defKey = defKey;
		}
	}
	
	public ArrayList<result> getResult() {
		return result;
	}
	public void setResult(ArrayList<result> result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}
}
