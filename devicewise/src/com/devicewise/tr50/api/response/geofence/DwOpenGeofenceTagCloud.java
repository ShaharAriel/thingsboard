package com.devicewise.tr50.api.response.geofence;

import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenGeofenceTagCloud extends DwOpenGenericResponse{
		
		private LinkedHashMap<String,Integer> result;

		public LinkedHashMap<String,Integer> getResult() {
			return result;
		}

		public void setResult(LinkedHashMap<String,Integer> result) {
			this.result = result;
		}
}
