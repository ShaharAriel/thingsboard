package com.devicewise.tr50.api.params;

public class DwOpenGatewayParam{
	
	private String make;
	private String model;
	private String dwProduct;
	private String dwPlatform;
	private String dwVersion;
	private String appVersion;
	private boolean remShell;
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDwProduct() {
		return dwProduct;
	}
	public void setDwProduct(String dwProduct) {
		this.dwProduct = dwProduct;
	}
	public String getDwPlatform() {
		return dwPlatform;
	}
	public void setDwPlatform(String dwPlatform) {
		this.dwPlatform = dwPlatform;
	}
	public String getDwVersion() {
		return dwVersion;
	}
	public void setDwVersion(String dwVersion) {
		this.dwVersion = dwVersion;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public boolean isRemShell() {
		return remShell;
	}
	public void setRemShell(boolean remShell) {
		this.remShell = remShell;
	}

}
