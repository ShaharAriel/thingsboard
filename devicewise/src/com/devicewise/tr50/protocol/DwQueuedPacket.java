package com.devicewise.tr50.protocol;

import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;

public class DwQueuedPacket {

	private Object response;
	private DwOpenReceiveActionListener recv;
	private DwPacket packet;
	private Exception exception;
	private int msgId;

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public DwOpenReceiveActionListener getRecv() {
		return recv;
	}

	public void setRecv(DwOpenReceiveActionListener recv) {
		this.recv = recv;
	}

	public DwPacket getPacket() {
		return packet;
	}

	public void setPacket(DwPacket packet) {
		this.packet = packet;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	
}
