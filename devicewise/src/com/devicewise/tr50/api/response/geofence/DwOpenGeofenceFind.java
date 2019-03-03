package com.devicewise.tr50.api.response.geofence;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenGeofenceFind extends DwOpenGenericResponse{
		
		private String id;
		private String key;
		private String name;
		private double radius;
		private ArrayList<String[]> points;
		private String createdBy;
		private String createdOn;
		private String updateBy;
		private String updatedOn;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getRadius() {
			return radius;
		}
		public void setRadius(double radius) {
			this.radius = radius;
		}
		public ArrayList<String[]> getPoints() {
			return points;
		}
		public void setPoints(ArrayList<String[]> points) {
			this.points = points;
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
		public String getUpdateBy() {
			return updateBy;
		}
		public void setUpdateBy(String updateBy) {
			this.updateBy = updateBy;
		}
		public String getUpdatedOn() {
			return updatedOn;
		}
		public void setUpdatedOn(String updatedOn) {
			this.updatedOn = updatedOn;
		}

}
