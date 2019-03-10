package com.devicewise.tr50.listeners;

import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxMessage;
import com.devicewise.tr50.protocol.DwClient;

public interface DwOpenMailboxEventListener {

	public void onReceive(DwClient client, int retCode, Throwable error, DwOpenMailboxMessage msg);
	
}
