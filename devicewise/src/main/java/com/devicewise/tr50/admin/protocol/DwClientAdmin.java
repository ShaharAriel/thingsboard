package com.devicewise.tr50.admin.protocol;

import com.devicewise.tr50.protocol.DwClient;

public abstract class DwClientAdmin extends DwClient{
	
	private DwOpenWorkerAdmin worker;
	
	public DwClientAdmin(){
		super();
		worker.initializeAdminWorker();
	}

	public DwOpenWorkerAdmin getWorker() {
		return worker;
	}

	public void setWorker(DwOpenWorkerAdmin worker) {
		this.worker = worker;
	}

}
