package com.devicewise.tr50.api.response.tunnel;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenTunnelFind extends DwOpenGenericResponse{

	private String tunId;
	private String tunName;
	private String tunVirtualHost;
	private int tunPort;
	private String routerKey;
	private String routerAddress;
	
	public String getTunId() {
		return tunId;
	}
	public void setTunId(String tunId) {
		this.tunId = tunId;
	}
	public String getTunName() {
		return tunName;
	}
	public void setTunName(String tunName) {
		this.tunName = tunName;
	}
	public String getTunVirtualHost() {
		return tunVirtualHost;
	}
	public void setTunVirtualHost(String tunVirtualHost) {
		this.tunVirtualHost = tunVirtualHost;
	}
	public int getTunPort() {
		return tunPort;
	}
	public void setTunPort(int tunPort) {
		this.tunPort = tunPort;
	}
	public String getRouterKey() {
		return routerKey;
	}
	public void setRouterKey(String routerKey) {
		this.routerKey = routerKey;
	}
	public String getRouterAddress() {
		return routerAddress;
	}
	public void setRouterAddress(String routerAddress) {
		this.routerAddress = routerAddress;
	}
	
}
