package com.devicewise.tr50.api.response.tunnel;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenTunnelRouterList extends DwOpenGenericResponse{

	private int count;
	private ArrayList<DwOpenTunnelRouterDef> routers;
	
	public static class DwOpenTunnelRouterDef{
	
		private String key;
		private String state;
		private String address;
		private String country;
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<DwOpenTunnelRouterDef> getRouters() {
		return routers;
	}

	public void setRouters(ArrayList<DwOpenTunnelRouterDef> routers) {
		this.routers = routers;
	}
}
