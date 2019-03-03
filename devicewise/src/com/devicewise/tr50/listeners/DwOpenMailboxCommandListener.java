package com.devicewise.tr50.listeners;

public abstract class DwOpenMailboxCommandListener implements DwOpenMailboxEventListener{
	
	private String command;
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
}
