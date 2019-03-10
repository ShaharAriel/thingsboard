package com.devicewise.tr50.api.response.diag;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenDiagTime extends DwOpenGenericResponse{
	
	private long time;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
}
