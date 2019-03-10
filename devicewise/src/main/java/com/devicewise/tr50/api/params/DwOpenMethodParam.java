package com.devicewise.tr50.api.params;

import java.util.ArrayList;

import com.devicewise.tr50.api.params.DwOpenThingDefMethod.NotificationVariable;

public class DwOpenMethodParam{
	
	private ArrayList<NotificationVariable> notificationVariables;

	public ArrayList<NotificationVariable> getNotificationVariables() {
		return notificationVariables;
	}

	public void setNotificationVariables(ArrayList<NotificationVariable> notificationVariables) {
		this.notificationVariables = notificationVariables;
	}
}
