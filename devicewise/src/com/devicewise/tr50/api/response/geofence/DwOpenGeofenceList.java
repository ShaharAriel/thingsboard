package com.devicewise.tr50.api.response.geofence;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenGeofenceList extends DwOpenGenericResponse{
	
		private ArrayList<DwOpenGeofenceFind> result;

		public ArrayList<DwOpenGeofenceFind> getResult() {
			return result;
		}

		public void setResult(ArrayList<DwOpenGeofenceFind> result) {
			this.result = result;
		}
}
