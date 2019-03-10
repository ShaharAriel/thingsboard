package com.devicewise.tr50.api.params;

import java.util.LinkedHashMap;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.devicewise.tr50.json.DwJsonObjectWithKey;

@JsonPropertyOrder({"name","tags","notificationVariables","completionVariables"})
@org.codehaus.jackson.annotate.JsonIgnoreProperties({"key"})
public class DwOpenThingDefMethod extends DwJsonObjectWithKey {

	private String name;
	private String[] tags;
	private LinkedHashMap<String,NotificationVariable> notificationVariables;
	private LinkedHashMap<String,CompletionVariable> completionVariables;

	@JsonPropertyOrder({"name","type","count","length"})
	@JsonIgnoreProperties({ "uiType", "uiTypeOptions" })
	public static class NotificationVariable{
		
		private String name;
		private String type;
		private int count;
		private int length;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
	}
	@JsonPropertyOrder({"name","type","count","length"})
	public static class CompletionVariable{
		
		private String name;
		private String type;
		private int count;
		private int length;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public LinkedHashMap<String,NotificationVariable> getNotificationVariables() {
		return notificationVariables;
	}
	public void setNotificationVariables(LinkedHashMap<String,NotificationVariable> notificationVariables) {
		this.notificationVariables = notificationVariables;
	}
	public LinkedHashMap<String,CompletionVariable> getCompletionVariables() {
		return completionVariables;
	}
	public void setCompletionVariables(LinkedHashMap<String,CompletionVariable> completionVariables) {
		this.completionVariables = completionVariables;
	}

}
