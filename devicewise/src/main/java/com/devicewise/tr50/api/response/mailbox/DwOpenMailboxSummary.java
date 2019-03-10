package com.devicewise.tr50.api.response.mailbox;

import org.codehaus.jackson.annotate.JsonProperty;

public class DwOpenMailboxSummary extends DwOpenMailboxItem{

	private SummaryEntry summary;

	public static class SummaryEntry {
		
		@JsonProperty("new")
		private int new_items;
		@JsonProperty("completed-error")
		private int completed_error;
		@JsonProperty("completed-ok")
		private int completed_ok;
		@JsonProperty("completed-auto")
		private int completed_auto;
		@JsonProperty("in-flight")
		private int in_flight;
		@JsonProperty("in-progress")
		private int in_progress;
		
		private int expired;
		private int purged;
		
		public int getNew_items() {
			return new_items;
		}
		public void setNew_items(int new_items) {
			this.new_items = new_items;
		}
		public int getCompleted_error() {
			return completed_error;
		}
		public void setCompleted_error(int completed_error) {
			this.completed_error = completed_error;
		}
		public int getCompleted_ok() {
			return completed_ok;
		}
		public void setCompleted_ok(int completed_ok) {
			this.completed_ok = completed_ok;
		}
		public int getCompleted_auto() {
			return completed_auto;
		}
		public void setCompleted_auto(int completed_auto) {
			this.completed_auto = completed_auto;
		}
		public int getIn_flight() {
			return in_flight;
		}
		public void setIn_flight(int in_flight) {
			this.in_flight = in_flight;
		}
		public int getIn_progress() {
			return in_progress;
		}
		public void setIn_progress(int in_progress) {
			this.in_progress = in_progress;
		}
		public int getExpired() {
			return expired;
		}
		public void setExpired(int expired) {
			this.expired = expired;
		}
		public int getPurged() {
			return purged;
		}
		public void setPurged(int purged) {
			this.purged = purged;
		}
	}

	public SummaryEntry getSummary() {
		return summary;
	}

	public void setSummary(SummaryEntry summary) {
		this.summary = summary;
	}

}
