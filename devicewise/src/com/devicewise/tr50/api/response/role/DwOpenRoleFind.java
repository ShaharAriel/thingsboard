package com.devicewise.tr50.api.response.role;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenRoleFind extends DwOpenGenericResponse{

	private String id;
	private String key;
	private String orgId;
	private String name;
	private String desc;
	private String[] perms;
	private String[] viewTags;
	private String[] updateTags;
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	
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

	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String[] getViewTags() {
		return viewTags;
	}
	public void setViewTags(String[] viewTags) {
		this.viewTags = viewTags;
	}
	public String[] getUpdateTags() {
		return updateTags;
	}
	public void setUpdateTags(String[] updateTags) {
		this.updateTags = updateTags;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String[] getPerms() {
		return perms;
	}
	public void setPerms(String[] perms) {
		this.perms = perms;
	}
}
