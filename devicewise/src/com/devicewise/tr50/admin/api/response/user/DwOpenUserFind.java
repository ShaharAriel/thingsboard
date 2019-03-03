package com.devicewise.tr50.admin.api.response.user;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenUserFind extends DwOpenGenericResponse{

	private String id;
	private String emailAddress;
	private String defaultOrgId;
	private String firstName;
	private String lastName;
	private String company;
	private String title;
	private String officePhone;
	private String mobilePhone;
	private boolean isSuperAdmin;
	private boolean isSuperOps;
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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getDefaultOrgId() {
		return defaultOrgId;
	}
	public void setDefaultOrgId(String defaultOrgId) {
		this.defaultOrgId = defaultOrgId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}
	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	public boolean isSuperOps() {
		return isSuperOps;
	}
	public void setSuperOps(boolean isSuperOps) {
		this.isSuperOps = isSuperOps;
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
	

	
}
	

