package com.devicewise.tr50.api.response.session;

import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenSessionFind extends DwOpenGenericResponse{
	
	protected String id;
	protected String orgId;
	protected String orgKey;
	protected String appId;
	protected String appName;
	protected String tokenId;
	protected String tokenName;
	protected String userId;
	protected String userName;
	protected String thingKey;
	protected String thingId;
	protected String whoAmI;
	protected boolean hasSuperAdmin;
	protected boolean hasSuperOps;
	protected ConnInfo connInfo;
	protected String[] readOnlyTags;
	protected String[] readWriteTags;
	protected boolean hasOrgAdmin;
	protected int ttl;
	protected String locale;
	protected String appToken;
	protected LinkedHashMap<String,Boolean> perms;
	
	public static class ConnInfo{
		private String protocol;
		private String remoteAddr;
		public String getProtocol() {
			return protocol;
		}
		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
		public String getRemoteAddr() {
			return remoteAddr;
		}
		public void setRemoteAddr(String remoteAddr) {
			this.remoteAddr = remoteAddr;
		}
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgKey() {
		return orgKey;
	}

	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getThingKey() {
		return thingKey;
	}

	public void setThingKey(String thingKey) {
		this.thingKey = thingKey;
	}

	public String getThingId() {
		return thingId;
	}

	public void setThingId(String thingId) {
		this.thingId = thingId;
	}

	public String getWhoAmI() {
		return whoAmI;
	}

	public void setWhoAmI(String whoAmI) {
		this.whoAmI = whoAmI;
	}

	public boolean isHasSuperAdmin() {
		return hasSuperAdmin;
	}

	public void setHasSuperAdmin(boolean hasSuperAdmin) {
		this.hasSuperAdmin = hasSuperAdmin;
	}

	public boolean isHasSuperOps() {
		return hasSuperOps;
	}

	public void setHasSuperOps(boolean hasSuperOps) {
		this.hasSuperOps = hasSuperOps;
	}

	public ConnInfo getConnInfo() {
		return connInfo;
	}

	public void setConnInfo(ConnInfo connInfo) {
		this.connInfo = connInfo;
	}

	public String[] getReadOnlyTags() {
		return readOnlyTags;
	}

	public void setReadOnlyTags(String[] readOnlyTags) {
		this.readOnlyTags = readOnlyTags;
	}

	public String[] getReadWriteTags() {
		return readWriteTags;
	}

	public void setReadWriteTags(String[] readWriteTags) {
		this.readWriteTags = readWriteTags;
	}

	public boolean isHasOrgAdmin() {
		return hasOrgAdmin;
	}

	public void setHasOrgAdmin(boolean hasOrgAdmin) {
		this.hasOrgAdmin = hasOrgAdmin;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}


	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public LinkedHashMap<String,Boolean> getPerms() {
		return perms;
	}

	public void setPerms(LinkedHashMap<String,Boolean> perms) {
		this.perms = perms;
	}

}
