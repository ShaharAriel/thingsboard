package com.devicewise.tr50.api.response.trigger;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenTriggerFind extends DwOpenGenericResponse{

	private String id;
	private String name;
	private String orgId;
	private String desc;
	private boolean started;
	private String eventType;
	private LinkedHashMap<String,Object> event;
	private ArrayList<LinkedHashMap<String,Object>> actions;
	private int execCount;
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	private int successCount;
	private int failureCount;
	private String lastFailure;
	private String lastSuccess;

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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public LinkedHashMap<String,Object> getEvent() {
		return event;
	}
	public void setEvent(LinkedHashMap<String,Object> event) {
		this.event = event;
	}
	public ArrayList<LinkedHashMap<String,Object>> getActions() {
		return actions;
	}
	public void setActions(ArrayList<LinkedHashMap<String,Object>> actions) {
		this.actions = actions;
	}
	public int getExecCount() {
		return execCount;
	}
	public void setExecCount(int execCount) {
		this.execCount = execCount;
	}
	public boolean isStarted() {
		return started;
	}
	public void setStarted(boolean started) {
		this.started = started;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public int getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}
	public String getLastFailure() {
		return lastFailure;
	}
	public void setLastFailure(String lastFailure) {
		this.lastFailure = lastFailure;
	}
	public String getLastSuccess() {
		return lastSuccess;
	}
	public void setLastSuccess(String lastSuccess) {
		this.lastSuccess = lastSuccess;
	}

}
