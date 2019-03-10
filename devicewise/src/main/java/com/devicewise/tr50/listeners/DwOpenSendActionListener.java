package com.devicewise.tr50.listeners;

import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;

public interface DwOpenSendActionListener {
	public void onSend(DwClient source, int responseCode, Throwable cause, DwPacket packet);
}
