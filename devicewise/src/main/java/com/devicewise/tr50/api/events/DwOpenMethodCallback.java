package com.devicewise.tr50.api.events;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxMessage;
import com.devicewise.tr50.constants.DwOpenServer;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenMailboxCommandListener;
import com.devicewise.tr50.protocol.DwClient;

public abstract class DwOpenMethodCallback extends DwOpenMailboxCommandListener{

	private String methodkey;
	private int retryAckCount=DwOpenServer.MAX_ACK_RETRY;
	private DwOpenMailboxMessage currentMailboxMessage;

	public DwOpenMethodCallback(String methodKey){
		this.methodkey = methodKey;
	}

	@Override
	public synchronized void onReceive(DwClient client, int retCode, Throwable error, DwOpenMailboxMessage msg) {

		if(retCode==0 && msg!=null){

			if(msg.getParams().get("method").toString().equals(methodkey)){
				try {
					setCurrentMailboxMessage(msg);
					LinkedHashMap<String,Object> out = new LinkedHashMap<String,Object>();
					StringReply errorMsg = new StringReply();
					@SuppressWarnings("unchecked")
					int ret=executeMethod(msg.getId(),(LinkedHashMap<String,Object>)msg.getParams().get("params"),out,errorMsg);
					DwOpenResponse response = new DwOpenResponse();

					response.setSuccess(false);
					retryAckCount=DwOpenServer.MAX_ACK_RETRY;
					int ackCode=-1;

					while((!response.isSuccess() || ackCode!=0) && retryAckCount>0){

						ackCode=client.getWorker().Mailbox().ack(msg.getId(),ret, errorMsg.getValue(), out, response);
						retryAckCount--;

					}

					if(ackCode!=0)
						this.onMethodAckFailure(ackCode, null, null);
					else if(!response.isSuccess())
						this.onMethodAckFailure(response.getErrorcodes()[0], response.getErrormessages()[0], null);


				} catch (IOException e) {
					this.onMethodAckFailure(-1,e.getLocalizedMessage(), e);
				} catch (DwOpenException e) {
					this.onMethodAckFailure(e.getErrorCode(), e.getLocalizedMessage(), e);
				}
			}
		}

	}

	public abstract int executeMethod(String execId,LinkedHashMap<String,Object> inParams, LinkedHashMap<String,Object> outParams, StringReply errorMessage);
	public abstract void onMethodAckFailure(int errorCode, String errorMessage, Throwable error);

	public String getMethodName() {
		return methodkey;
	}

	public void setMethodName(String methodName) {
		this.methodkey = methodName;
	}

	public DwOpenMailboxMessage getCurrentMailboxMessage() {
		return currentMailboxMessage;
	}

	public void setCurrentMailboxMessage(DwOpenMailboxMessage currentMailboxMessage) {
		this.currentMailboxMessage = currentMailboxMessage;
	}

}
