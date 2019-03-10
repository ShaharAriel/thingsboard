package com.devicewise.tr50.listeners;

import com.devicewise.tr50.protocol.DwClient;

public interface DwOpenDisconnectActionListener {
	public void onDisconnect(DwClient source, Throwable cause);
}
