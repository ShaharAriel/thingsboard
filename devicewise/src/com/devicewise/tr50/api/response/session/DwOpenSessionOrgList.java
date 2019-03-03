package com.devicewise.tr50.api.response.session;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenSessionOrgList extends DwOpenGenericResponse{
	
	private ArrayList<Org> result;
	
	public static class Org{
	
		private String orgId;
		private String orgKey;
		private ArrayList<String> roles;
		
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
		public ArrayList<String> getRoles() {
			return roles;
		}
		public void setRoles(ArrayList<String> roles) {
			this.roles = roles;
		}
	}	
	
	public ArrayList<Org> getResult() {
		return result;
	}

	public void setResult(ArrayList<Org> result) {
		this.result = result;
	}
}
