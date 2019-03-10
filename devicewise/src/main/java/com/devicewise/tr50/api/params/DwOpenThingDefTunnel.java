package com.devicewise.tr50.api.params;

import com.devicewise.tr50.json.DwJsonObjectWithKey;
@org.codehaus.jackson.annotate.JsonIgnoreProperties({"key"})
public class DwOpenThingDefTunnel extends DwJsonObjectWithKey {
	
	private String name;
	private int port;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
