package com.devicewise.tr50.api.response.role;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenRolePerms extends DwOpenGenericResponse{

	private String[] app;
	private String[] cellular;
	private String[] config;
	private String[] diag;
	private String[] email;
	private String[] file;
	private String[] geofence;
	private String[] location;
	private String[] log;
	private String[] mailbox;
	private String[] method;
	private String[] ota;
	private String[] property;
	private String[] session;
	private String[] test;
	private String[] thing;
	private String[] thing_def;
	private String[] trigger;
	private String[] twilio;
	private String[] usage;
	private String[] user;
	
	public String[] getApp() {
		return app;
	}
	public void setApp(String[] app) {
		this.app = app;
	}
	public String[] getCellular() {
		return cellular;
	}
	public void setCellular(String[] cellular) {
		this.cellular = cellular;
	}
	public String[] getConfig() {
		return config;
	}
	public void setConfig(String[] config) {
		this.config = config;
	}
	public String[] getDiag() {
		return diag;
	}
	public void setDiag(String[] diag) {
		this.diag = diag;
	}
	public String[] getEmail() {
		return email;
	}
	public void setEmail(String[] email) {
		this.email = email;
	}
	public String[] getFile() {
		return file;
	}
	public void setFile(String[] file) {
		this.file = file;
	}
	public String[] getLocation() {
		return location;
	}
	public void setLocation(String[] location) {
		this.location = location;
	}
	public String[] getLog() {
		return log;
	}
	public void setLog(String[] log) {
		this.log = log;
	}
	public String[] getMailbox() {
		return mailbox;
	}
	public void setMailbox(String[] mailbox) {
		this.mailbox = mailbox;
	}
	public String[] getMethod() {
		return method;
	}
	public void setMethod(String[] method) {
		this.method = method;
	}
	public String[] getOta() {
		return ota;
	}
	public void setOta(String[] ota) {
		this.ota = ota;
	}
	public String[] getProperty() {
		return property;
	}
	public void setProperty(String[] property) {
		this.property = property;
	}
	public String[] getSession() {
		return session;
	}
	public void setSession(String[] session) {
		this.session = session;
	}
	public String[] getTest() {
		return test;
	}
	public void setTest(String[] test) {
		this.test = test;
	}
	public String[] getThing() {
		return thing;
	}
	public void setThing(String[] thing) {
		this.thing = thing;
	}
	public String[] getThing_def() {
		return thing_def;
	}
	public void setThing_def(String[] thing_def) {
		this.thing_def = thing_def;
	}
	public String[] getTrigger() {
		return trigger;
	}
	public void setTrigger(String[] trigger) {
		this.trigger = trigger;
	}
	public String[] getTwilio() {
		return twilio;
	}
	public void setTwilio(String[] twilio) {
		this.twilio = twilio;
	}
	public String[] getUsage() {
		return usage;
	}
	public void setUsage(String[] usage) {
		this.usage = usage;
	}
	public String[] getUser() {
		return user;
	}
	public void setUser(String[] user) {
		this.user = user;
	}
	public String[] getGeofence() {
		return geofence;
	}
	public void setGeofence(String[] geofence) {
		this.geofence = geofence;
	}
	
}
