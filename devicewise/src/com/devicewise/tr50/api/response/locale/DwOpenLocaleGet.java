package com.devicewise.tr50.api.response.locale;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenLocaleGet extends DwOpenGenericResponse{
	
	private String locale;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
