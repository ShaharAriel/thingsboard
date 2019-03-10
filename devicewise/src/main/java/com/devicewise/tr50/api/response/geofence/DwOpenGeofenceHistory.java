package com.devicewise.tr50.api.response.geofence;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenGeofenceHistory extends DwOpenGenericResponse{
	
	private ArrayList<GeoFenceEntry> values;
	
	public static class GeoFenceEntry{
		
		private String id;
		private String type;
		private String thingId;
		private String geofenceKey;
		private String ts;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getThingId() {
			return thingId;
		}
		public void setThingId(String thingId) {
			this.thingId = thingId;
		}
		public String getGeofenceKey() {
			return geofenceKey;
		}
		public void setGeofenceKey(String geofenceKey) {
			this.geofenceKey = geofenceKey;
		}
		public String getTs() {
			return ts;
		}
		public void setTs(String ts) {
			this.ts = ts;
		}
		
	}

	public ArrayList<GeoFenceEntry> getValues() {
		return values;
	}

	public void setValues(ArrayList<GeoFenceEntry> values) {
		this.values = values;
	}

}
