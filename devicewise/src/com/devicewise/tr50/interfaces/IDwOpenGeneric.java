package com.devicewise.tr50.interfaces;

import java.io.IOException;

import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;

public interface IDwOpenGeneric {
	
	public abstract int sendParamsWithReply(String command, String corrId, Object response, Object... params) throws IOException,DwOpenException;
	public abstract int sendParamsWithReplyAsync(String command, String corrId, DwOpenReceiveActionListener recv, Object response, Object... params) throws IOException,DwOpenException;

}
