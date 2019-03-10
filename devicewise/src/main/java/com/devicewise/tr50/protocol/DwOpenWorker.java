package com.devicewise.tr50.protocol;

import com.devicewise.tr50.api.DwOpenAlarm;
import com.devicewise.tr50.api.DwOpenConfig;
import com.devicewise.tr50.api.DwOpenDiag;
import com.devicewise.tr50.api.DwOpenEmail;
import com.devicewise.tr50.api.DwOpenFile;
import com.devicewise.tr50.api.DwOpenGateway;
import com.devicewise.tr50.api.DwOpenGeneric;
import com.devicewise.tr50.api.DwOpenLocale;
import com.devicewise.tr50.api.DwOpenLocation;
import com.devicewise.tr50.api.DwOpenLog;
import com.devicewise.tr50.api.DwOpenMailbox;
import com.devicewise.tr50.api.DwOpenMethod;
import com.devicewise.tr50.api.DwOpenModule;
import com.devicewise.tr50.api.DwOpenProperty;
import com.devicewise.tr50.api.DwOpenRole;
import com.devicewise.tr50.api.DwOpenSession;
import com.devicewise.tr50.api.DwOpenThing;
import com.devicewise.tr50.api.DwOpenThingDef;
import com.devicewise.tr50.api.DwOpenThirdParty;
import com.devicewise.tr50.api.DwOpenTrigger;
import com.devicewise.tr50.api.DwOpenTunnel;
import com.devicewise.tr50.api.DwOpenUsage;
import com.devicewise.tr50.interfaces.IDwOpenWorker;

public class DwOpenWorker implements IDwOpenWorker {
	
	protected DwClient client;

	public DwOpenWorker(DwClient client){
		this.setClient(client);
	}

	private DwOpenLocation location = null;
	public DwOpenLocation Location(){
		return location;
	}
	
	private DwOpenProperty property = null;
	public DwOpenProperty Property(){
		return property;
	}
	
	private DwOpenThing thing = null;
	public DwOpenThing Thing(){
		return thing;
	}
	
	private DwOpenThingDef thingDef = null;
	public DwOpenThingDef ThingDef(){
		return thingDef;
	}

	private DwOpenEmail email = null;
	public DwOpenEmail Email(){
		return email;
	}
	
	private DwOpenLocale locale = null;
	public DwOpenLocale Locale(){
		return locale;
	}
	
	private DwOpenConfig config = null;
	public DwOpenConfig Config(){
		return config;
	}
	
	private DwOpenDiag diag = null;
	public DwOpenDiag Diag(){
		return diag;
	}
	
	private DwOpenGateway gateway = null;
	public DwOpenGateway Gateway(){
		return gateway;
	}
	
	private DwOpenGeneric generic = null;
	public DwOpenGeneric Generic(){
		return generic;
	}

	private DwOpenLog log = null;
	public DwOpenLog Log(){
		return log;
	}
	
	private DwOpenSession session = null;
	public DwOpenSession Session(){
		return session;
	}
	
	private DwOpenFile file = null;
	public DwOpenFile File(){
		return file;
	}
	
	private DwOpenTrigger trigger = null;
	public DwOpenTrigger Trigger(){
		return trigger;
	}
	
	private DwOpenRole role = null;
	public DwOpenRole Role(){
		return role;
	}
	
	private DwOpenAlarm alarm = null;
	public DwOpenAlarm Alarm(){
		return alarm;
	}
	
	private DwOpenMailbox mailbox = null;
	public DwOpenMailbox Mailbox(){
		return mailbox;
	}
	
	private DwOpenMethod method = null;
	public DwOpenMethod Method(){
		return method;
	}
	
	private DwOpenModule module = null;
	public DwOpenModule Module(){
		return module;
	}
	
	private DwOpenThirdParty thirdparty = null;
	public DwOpenThirdParty ThirdParty(){
		return thirdparty;
	}
	
	private DwOpenTunnel tunnel = null;
	public DwOpenTunnel Tunnel(){
		return tunnel;
	}
	
	private DwOpenUsage usage = null;
	public DwOpenUsage Usage(){
		return usage;
	}
	
	public void initializeWorker(){

		alarm =  new DwOpenAlarm(this.client);
		config = new DwOpenConfig(this.client);
		diag = new DwOpenDiag(this.client);
		email = new DwOpenEmail(this.client);
		file = new DwOpenFile(this.client);
		gateway = new DwOpenGateway(this.client);
		generic = new DwOpenGeneric(this.client);
		locale = new DwOpenLocale(this.client);
		location = new DwOpenLocation(this.client);
		log = new DwOpenLog(this.client);
		mailbox = new DwOpenMailbox(this.client);
		method = new DwOpenMethod(this.client);
		module = new DwOpenModule(this.client);
		property = new DwOpenProperty(this.client);
		role = new DwOpenRole(this.client);
		session = new DwOpenSession(this.client);
		thing = new DwOpenThing(this.client);
		thingDef = new DwOpenThingDef(this.client);
		thirdparty = new DwOpenThirdParty(this.client);
		trigger = new DwOpenTrigger(this.client);
		tunnel = new DwOpenTunnel(this.client);
		usage = new DwOpenUsage(this.client);
	}
	
	public DwClient getClient() {
		return client;
	}

	public void setClient(DwClient client) {
		this.client = client;
	}
	
}
