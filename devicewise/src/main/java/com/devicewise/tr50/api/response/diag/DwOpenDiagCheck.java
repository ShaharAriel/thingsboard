package com.devicewise.tr50.api.response.diag;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenDiagCheck extends DwOpenGenericResponse{
	
	private boolean readOnlyAccess;
	private boolean readWriteAccess;
	
	public boolean isReadOnlyAccess() {
		return readOnlyAccess;
	}
	public void setReadOnlyAccess(boolean readOnlyAccess) {
		this.readOnlyAccess = readOnlyAccess;
	}
	public boolean isReadWriteAccess() {
		return readWriteAccess;
	}
	public void setReadWriteAccess(boolean readWriteAccess) {
		this.readWriteAccess = readWriteAccess;
	}

}
