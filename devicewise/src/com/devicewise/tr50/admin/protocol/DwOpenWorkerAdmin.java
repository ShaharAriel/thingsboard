package com.devicewise.tr50.admin.protocol;

import com.devicewise.tr50.admin.api.DwOpenApp;
import com.devicewise.tr50.admin.api.DwOpenOrg;
import com.devicewise.tr50.admin.api.DwOpenUser;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwOpenWorker;

public class DwOpenWorkerAdmin extends DwOpenWorker{

	public DwOpenWorkerAdmin(DwClient client) {
		super(client);
		// TODO Auto-generated constructor stub
	}
	
	private DwOpenUser user = null;
	public DwOpenUser User(){
		return user;
	}
	
	private DwOpenOrg org = null;
	public DwOpenOrg Org(){
		return org;
	}
	
	private DwOpenApp app = null;
	public DwOpenApp App(){
		return app;
	}

	public void initializeAdminWorker(){
		app =  new DwOpenApp(this.client);
		org = new DwOpenOrg(this.client);
		user = new DwOpenUser(this.client);
	}
}
