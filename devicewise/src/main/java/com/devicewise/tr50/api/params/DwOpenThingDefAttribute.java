package com.devicewise.tr50.api.params;

import com.devicewise.tr50.json.DwJsonObjectWithKey;

@org.codehaus.jackson.annotate.JsonIgnoreProperties({"key"})
public class DwOpenThingDefAttribute extends DwJsonObjectWithKey {

	private String name;
	private String[] viewTags;
	private String[] updateTags;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
