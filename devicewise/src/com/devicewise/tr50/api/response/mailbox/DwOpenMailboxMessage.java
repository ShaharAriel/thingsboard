package com.devicewise.tr50.api.response.mailbox;

import java.util.LinkedHashMap;

public class DwOpenMailboxMessage extends DwOpenMailboxItem{
	
	private LinkedHashMap<String,Object> params;

	public LinkedHashMap<String,Object> getParams() {
		return params;
	}

	public void setParams(LinkedHashMap<String,Object> params) {
		this.params = params;
	}

}
