package com.devicewise.tr50.api.response;

public class DwOpenAuthResponse extends DwOpenGenericResponse{

	private String orgKey;
	private String sessionId;
	
	public String getOrgKey() {
		return orgKey;
	}
	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionID) {
		this.sessionId = sessionID;
	}

}
