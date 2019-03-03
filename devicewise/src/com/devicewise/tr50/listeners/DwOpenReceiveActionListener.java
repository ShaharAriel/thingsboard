package com.devicewise.tr50.listeners;

import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;

public interface DwOpenReceiveActionListener {
	public void onReceive(DwClient source, int responseCode, Throwable cause, DwPacket packet, Object response);
}
