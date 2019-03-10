package com.devicewise.tr50.api.response.geofence;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenGeofenceLocate extends DwOpenGenericResponse{
		
		private ArrayList<String> result;

		public ArrayList<String> getResult() {
			return result;
		}

		public void setResult(ArrayList<String> result) {
			this.result = result;
		}

}
