package com.devicewise.tr50.api.response.mailbox;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenMailboxCheck extends DwOpenGenericResponse{

	private ArrayList<DwOpenMailboxMessage> messages;

	public ArrayList<DwOpenMailboxMessage> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<DwOpenMailboxMessage> messages) {
		this.messages = messages;
	}
	
}
