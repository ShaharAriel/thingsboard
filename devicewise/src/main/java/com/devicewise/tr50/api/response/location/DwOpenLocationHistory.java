package com.devicewise.tr50.api.response.location;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;
import com.devicewise.tr50.api.response.location.DwOpenLocationEncodeDecode.Address;

public class DwOpenLocationHistory extends DwOpenGenericResponse{
	
	private ArrayList<values> values;
	
	public static class values{
		
		private String id;
		private String ts;
		private String thingId;
		private String corrId;
		private double lat;
		private double lng;
		private double heading;
		private double altitude;
		private double delta;
		private Address address;
		private double fixAcc;
		private double speed;
		private String fixType;

		public String getTs() {
			return ts;
		}
		public void setTs(String ts) {
			this.ts = ts;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getThingId() {
			return thingId;
		}
		public void setThingId(String thingId) {
			this.thingId = thingId;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
		public double getHeading() {
			return heading;
		}
		public void setHeading(double heading) {
			this.heading = heading;
		}
		public double getAltitude() {
			return altitude;
		}
		public void setAltitude(double altitude) {
			this.altitude = altitude;
		}
		public double getDelta() {
			return delta;
		}
		public void setDelta(double delta) {
			this.delta = delta;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public String getCorrId() {
			return corrId;
		}
		public void setCorrId(String corrId) {
			this.corrId = corrId;
		}
		public String getFixType() {
			return fixType;
		}
		public void setFixType(String fixType) {
			this.fixType = fixType;
		}
		public double getSpeed() {
			return speed;
		}
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		public double getFixAcc() {
			return fixAcc;
		}
		public void setFixAcc(double fixAcc) {
			this.fixAcc = fixAcc;
		}
	}

	public ArrayList<values> getValues() {
		return values;
	}

	public void setValues(ArrayList<values> values) {
		this.values = values;
	}

}
