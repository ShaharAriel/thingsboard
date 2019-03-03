package com.devicewise.tr50.api.response.property;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenPropertyStats extends DwOpenGenericResponse{
	
	private int propertyRecords;
	private int aggregateRecords;
	private String firstPublish;
	private String lastPublish;

	public int getPropertyRecords() {
		return propertyRecords;
	}

	public void setPropertyRecords(int propertyRecords) {
		this.propertyRecords = propertyRecords;
	}

	public int getAggregateRecords() {
		return aggregateRecords;
	}

	public void setAggregateRecords(int aggregateRecords) {
		this.aggregateRecords = aggregateRecords;
	}

	public String getFirstPublish() {
		return firstPublish;
	}

	public void setFirstPublish(String firstPublish) {
		this.firstPublish = firstPublish;
	}

	public String getLastPublish() {
		return lastPublish;
	}

	public void setLastPublish(String lastPublish) {
		this.lastPublish = lastPublish;
	}

}
