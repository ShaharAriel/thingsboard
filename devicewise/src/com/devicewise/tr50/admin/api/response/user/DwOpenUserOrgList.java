package com.devicewise.tr50.admin.api.response.user;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenUserOrgList extends DwOpenGenericResponse{
	
	private ArrayList<Result> result;
	
	public static class Result{
		
		private String orgId;
		private String orgKey;
		private String[] roles;
		private Boolean isOrgAdmin;
		
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
		public Boolean getIsOrgAdmin() {
			return isOrgAdmin;
		}
		public void setIsOrgAdmin(Boolean isOrgAdmin) {
			this.isOrgAdmin = isOrgAdmin;
		}
		public String[] getRoles() {
			return roles;
		}
		public void setRoles(String[] roles) {
			this.roles = roles;
		}
	}

	public ArrayList<Result> getResult() {
		return result;
	}

	public void setResult(ArrayList<Result> result) {
		this.result = result;
	}

}
