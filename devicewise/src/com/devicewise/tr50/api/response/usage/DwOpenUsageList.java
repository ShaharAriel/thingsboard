package com.devicewise.tr50.api.response.usage;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenUsageList extends DwOpenGenericResponse{
	
	private int count;
	private ArrayList<Result> result;
	
	public static class Result{
		
		private String ts;
		private String processedBy;
		private String orgId;
		private String orgKey;
		private String whoami;
		private String request;
		private String response;
		
		public String getTs() {
			return ts;
		}
		public void setTs(String ts) {
			this.ts = ts;
		}
		public String getProcessedBy() {
			return processedBy;
		}
		public void setProcessedBy(String processedBy) {
			this.processedBy = processedBy;
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
		public String getWhoami() {
			return whoami;
		}
		public void setWhoami(String whoami) {
			this.whoami = whoami;
		}
		public String getRequest() {
			return request;
		}
		public void setRequest(String request) {
			this.request = request;
		}
		public String getResponse() {
			return response;
		}
		public void setResponse(String response) {
			this.response = response;
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<Result> getResult() {
		return result;
	}

	public void setResult(ArrayList<Result> result) {
		this.result = result;
	}

}
