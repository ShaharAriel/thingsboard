package com.devicewise.tr50.api.response.thingdef;

import java.util.LinkedHashMap;

import com.devicewise.tr50.api.params.DwOpenAlarmParam;
import com.devicewise.tr50.api.params.DwOpenThingDefAttribute;
import com.devicewise.tr50.api.params.DwOpenThingDefMethod;
import com.devicewise.tr50.api.params.DwOpenThingDefProperty;
import com.devicewise.tr50.api.params.DwOpenThingDefTunnel;
import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenThingDefFind extends DwOpenGenericResponse{

	private String id;
	private String key;
	private boolean autoDefProps = false;
	private boolean autoDefAttrs = false;
	private String name;
	private LinkedHashMap <String,DwOpenThingDefAttribute> attributes;
	private LinkedHashMap <String,DwOpenThingDefProperty> properties;
	private LinkedHashMap <String,DwOpenThingDefMethod> methods;
	private LinkedHashMap <String,DwOpenThingDefTunnel> tunnels;
	private LinkedHashMap <String,DwOpenAlarmParam> alarms;
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	private int version;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public LinkedHashMap <String,DwOpenThingDefProperty> getProperties() {
		return properties;
	}
	public void setProperties(LinkedHashMap <String,DwOpenThingDefProperty> properties) {
		this.properties = properties;
	}
	public LinkedHashMap <String,DwOpenThingDefMethod> getMethods() {
		return methods;
	}
	public void setMethods(LinkedHashMap <String,DwOpenThingDefMethod> methods) {
		this.methods = methods;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isAutoDefProps() {
		return autoDefProps;
	}
	public void setAutoDefProps(boolean autoDefProps) {
		this.autoDefProps = autoDefProps;
	}
	public LinkedHashMap <String,DwOpenAlarmParam> getAlarms() {
		return alarms;
	}
	public void setAlarms(LinkedHashMap <String,DwOpenAlarmParam> alarms) {
		this.alarms = alarms;
	}
	public boolean isAutoDefAttrs() {
		return autoDefAttrs;
	}
	public void setAutoDefAttrs(boolean autoDefAttrs) {
		this.autoDefAttrs = autoDefAttrs;
	}
	public LinkedHashMap <String,DwOpenThingDefAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(LinkedHashMap <String,DwOpenThingDefAttribute> attributes) {
		this.attributes = attributes;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public LinkedHashMap <String,DwOpenThingDefTunnel> getTunnels() {
		return tunnels;
	}
	public void setTunnels(LinkedHashMap <String,DwOpenThingDefTunnel> tunnels) {
		this.tunnels = tunnels;
	}
}
