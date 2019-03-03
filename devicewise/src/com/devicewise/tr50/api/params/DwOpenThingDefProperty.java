package com.devicewise.tr50.api.params;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.devicewise.tr50.json.DwJsonObjectWithKey;

@JsonPropertyOrder({"name","prefix","suffix","unit","viewTags","updateTags"})
@org.codehaus.jackson.annotate.JsonIgnoreProperties({"key"})
public class DwOpenThingDefProperty extends DwJsonObjectWithKey {

	private String name;
	private String unit;
	private String prefix;
	private String suffix;
	private String[] viewTags;
	private String[] updateTags;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String value) {
		this.unit = value;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
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

}
