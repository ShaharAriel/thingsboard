package com.devicewise.tr50.protocol;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import com.devicewise.tr50.api.events.DwOpenMethodCallback;
import com.devicewise.tr50.api.response.DwOpenAuthResponse;
import com.devicewise.tr50.api.response.DwOpenGenericResponse;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxCheck;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxMessage;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.interfaces.IDwClient;
import com.devicewise.tr50.listeners.DwOpenDisconnectActionListener;
import com.devicewise.tr50.listeners.DwOpenMailboxCommandListener;
import com.devicewise.tr50.listeners.DwOpenMailboxEventListener;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.listeners.DwOpenSendActionListener;

public abstract class DwClient implements IDwClient{

	private String sessionId;
	private String orgKey;
	private DwPacket auth;
	private DwOpenWorker worker;
	private static Integer msgId = 0;

	protected HashMap<Integer,DwQueuedPacket> queue;
	protected Vector<DwOpenReceiveActionListener> recvListeners;
	protected Vector<DwOpenSendActionListener> sendListeners;
	protected Vector<DwOpenDisconnectActionListener> disconnectListeners;
	protected Vector<DwOpenMailboxEventListener> mailboxListeners;

	public DwClient(){
		worker = new DwOpenWorker(this);
		worker.initializeWorker();
		queue = new HashMap<Integer,DwQueuedPacket>();
		recvListeners = new Vector<DwOpenReceiveActionListener>();
		sendListeners = new Vector<DwOpenSendActionListener>();
		disconnectListeners = new Vector<DwOpenDisconnectActionListener>();
		mailboxListeners = new Vector<DwOpenMailboxEventListener>();
	}

	public DwOpenWorker getWorker(){
		return worker;
	}

	public int authenticate(String appToken, String appID, String thingKey) throws IOException, DwOpenException{

		int ret=0;
		auth = new DwPacket();
		DwCommand cmd = new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_AUTH);
		cmd.addParamsFromStringArrayNameValuePairs("appToken",appToken,"appId",appID,"thingKey",thingKey);
		auth.addCommand(DwOpenCommands.CORR_ID_AUTH,cmd);
		ret=sendPacket(auth);

		if(ret==0){
			DwOpenAuthResponse resp = new DwOpenAuthResponse();
			resp.parseResponse(auth.getResponse());

			if(!resp.isSuccess())
				return((resp.getErrorcodes()!=null && resp.getErrorcodes().length!=0)?resp.getErrorcodes()[0]:DwOpenErrors.PROTOCOL_CLIENT_AUTHENTICATION_FAILED);

			this.sessionId=resp.getSessionId();
		}
		return ret;

	}

	public int authenticate(String user, String password) throws IOException, DwOpenException{

		int ret=0;
		auth = new DwPacket();
		DwCommand cmd = new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_AUTH);
		cmd.addParamsFromStringArrayNameValuePairs("username",user,"password",password);
		auth.addCommand(DwOpenCommands.CORR_ID_AUTH,cmd);
		ret = sendPacket(auth);

		if(ret==0){
			DwOpenAuthResponse resp = new DwOpenAuthResponse();
			resp.parseResponse(auth.getResponse());

			if(!resp.isSuccess())
				return((resp.getErrorcodes()!=null && resp.getErrorcodes().length!=0)?resp.getErrorcodes()[0]:DwOpenErrors.PROTOCOL_CLIENT_AUTHENTICATION_FAILED);

			this.sessionId=resp.getSessionId();
		}
		return ret;
	}

	public int sendPacket(DwPacket packet) throws DwOpenException{

		int ret=0;
		StringReply reply = new StringReply();
		DwQueuedPacket qpkt  = new DwQueuedPacket();
		qpkt.setPacket(packet);

		synchronized(queue){

			qpkt.setMsgId(msgId++);
			addQueue(qpkt.getMsgId(),qpkt);
		}

		ret=sendJSONRequest(qpkt.getMsgId(),packet.getRequest(),reply);
		if(ret==0)
			packet.setResponse(reply.getValue());

		synchronized(queue){
			removeQueue(qpkt.getMsgId());
		}

		return(ret);
	}

	public int sendPacket(DwPacket packet, DwOpenReceiveActionListener recv, Object response){

		int ret=0;
		DwQueuedPacket qpkt  = new DwQueuedPacket();
		qpkt.setPacket(packet);
		qpkt.setRecv(recv);
		qpkt.setResponse(response);

		synchronized(queue){

			qpkt.setMsgId(msgId++);
			addQueue(qpkt.getMsgId(),qpkt);
		}


		try {
			if((ret=sendAsyncJSONRequest(qpkt.getMsgId(),packet.getRequest()))!=0){
				removeQueue(qpkt.getMsgId());
			}

			onSend(ret,null,packet);

		} catch (DwOpenException e) {
			synchronized(queue){
				removeQueue(qpkt.getMsgId());
			}
			onSend(ret,e,packet);
		}
		return(ret);

	}

	public synchronized void receiveNotify(){

		DwOpenMailboxCheck checkResponse  = new DwOpenMailboxCheck();

		int ret;
		try {

			ret = this.worker.Mailbox().check(false, null, checkResponse);

			if(ret!=0)
				onMailboxReceive(ret,null,null);

			if(checkResponse.isSuccess()){
				for(DwOpenMailboxMessage message:checkResponse.getMessages()){
					onMailboxReceive(0,null,message);
				}
			}

			else
				onMailboxReceive(checkResponse.getErrorcodes()[0],new DwOpenException(checkResponse.getErrorcodes()[0],checkResponse.getErrormessages()[0]),null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			onMailboxReceive(-1,e,null);
		} catch (DwOpenException e) {
			// TODO Auto-generated catch block
			onMailboxReceive(e.getErrorCode(),e,null);
		}
	}

	public abstract int connect() throws DwOpenException;
	public abstract int disconnect();

	public abstract int sendJSONRequest(int msgId,String json, StringReply reply) throws DwOpenException;
	public abstract int sendAsyncJSONRequest(int msgId, String json) throws DwOpenException;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOrgKey() {
		return orgKey;
	}

	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}

	public HashMap<Integer,DwQueuedPacket> getQueue() {
		return queue;
	}

	public void setQueue(HashMap<Integer,DwQueuedPacket> _queue) {
		queue = _queue;
	}

	public void addQueue(int msgIdHash,DwQueuedPacket qPkt){
		queue.put(msgIdHash, qPkt);
	}

	public void removeQueue(int msgIdHash){
		if(queue.containsKey(msgIdHash))
			queue.remove(msgIdHash);
	}

	public int getMsgId() {
		return msgId;
	}

	public void incrementMsgId(){
		msgId++;
	}

	public void onSend(int retCode,Throwable cause,DwPacket packet){
		for(DwOpenSendActionListener listener:sendListeners)
			listener.onSend(this, retCode, cause,packet);
	}

	public void onReceive(int retCode,Throwable cause, DwPacket packet, DwOpenGenericResponse response){
		for(DwOpenReceiveActionListener listener:recvListeners)
			listener.onReceive(this, retCode, cause,packet, response);
	}

	public void onMailboxReceive(int retCode,Throwable cause, DwOpenMailboxMessage response){
		
		for(DwOpenMailboxEventListener listener:mailboxListeners){
			
			if(response!=null && listener instanceof DwOpenMailboxCommandListener && ((DwOpenMailboxCommandListener)listener).getCommand()!=null){
				if(response.getCommand().equals(((DwOpenMailboxCommandListener)listener).getCommand()))
					listener.onReceive(this,retCode, cause, response);
			}
			else{
				listener.onReceive(this,retCode, cause, response);
			}
			
		}
			
	}

	public void onDisconnect(DwClient source, Throwable cause){
		for(DwOpenDisconnectActionListener listener:disconnectListeners)
			listener.onDisconnect(this, cause);
	}

	public int registerMethod(DwOpenMethodCallback method){
		if(method==null || method.getMethodName()==null)
			return DwOpenErrors.API_METHOD_UNINITIALIZED;
		else{
			for(DwOpenMailboxEventListener event:mailboxListeners){
				if(event instanceof DwOpenMethodCallback && ((DwOpenMethodCallback)event).getMethodName().equals(method.getMethodName()))
					return DwOpenErrors.API_METHOD_ALREADY_REGISTERED;
			}
		}

		return(registerMailboxCommandListener(DwOpenCommands.CMD_API_METHOD_EXEC,method));
	}
	
	public int unregisterMethod(DwOpenMethodCallback method){
			
		if(unregisterMailboxEventListener(method)!=0)
				return DwOpenErrors.API_METHOD_NOT_REGISTERED;
		
		return (0);
		
	}

	public void registerSendActionListener(DwOpenSendActionListener listener){
		sendListeners.add(listener);
	}

	public int registerMailboxEventListener(DwOpenMailboxEventListener listener){
		if(mailboxListeners.contains(listener))
			return DwOpenErrors.API_MAILBOX_ITEM_ALREADY_REGISTERED;
		mailboxListeners.add(listener);
		return 0;
	}
	
	public int registerMailboxCommandListener(String command,DwOpenMailboxCommandListener listener){
		
		if(!command.equals(DwOpenCommands.CMD_API_METHOD_EXEC)){
		
			for(DwOpenMailboxEventListener event:mailboxListeners){
				if(event instanceof DwOpenMailboxCommandListener && ((DwOpenMailboxCommandListener)event).getCommand()!=null && ((DwOpenMailboxCommandListener)event).getCommand().equals(command))
					return DwOpenErrors.API_MAILBOX_COMMAND_ALREADY_REGISTERED;
			}
		}

		listener.setCommand(command);
		return(registerMailboxEventListener(listener));
	}
	

	public void registerReceiveActionListener(DwOpenReceiveActionListener listener){
		recvListeners.add(listener);		
	}

	public void registerDisconnectActionListener(DwOpenDisconnectActionListener listener){
		disconnectListeners.add(listener);
	}
	
	public int unregisterMailboxCommandListener(DwOpenMailboxCommandListener listener){
		if(unregisterMailboxEventListener(listener)!=0)
			return DwOpenErrors.API_UNKNOWN_MAILBOX_COMMAND_OBJECT;
		return 0;
	}

	public int unregisterSendActionListener(DwOpenSendActionListener listener){

		if(sendListeners.contains(listener))	
			sendListeners.remove(listener);

		else
			return DwOpenErrors.API_UNKNOWN_SENDLISTENER_OBJECT;
		return 0;
	}

	public int unregisterMailboxEventListener(DwOpenMailboxEventListener listener){

		if(mailboxListeners.contains(listener))
			mailboxListeners.remove(listener);
		else
			return DwOpenErrors.API_UNKNOWN_MAILBOX_ITEM_OBJECT;

		return 0;

	}

	public int unregisterReceiveActionListener(DwOpenReceiveActionListener listener){

		if(recvListeners.contains(listener))	
			recvListeners.remove(listener);

		else
			return DwOpenErrors.API_UNKNOWN_RECEIVELISTENER_OBJECT;
		return 0;
	}

	public int unregisterDisconnectActionListener(DwOpenDisconnectActionListener listener){

		if(disconnectListeners.contains(listener))
			disconnectListeners.remove(listener);
		else
			return DwOpenErrors.API_UNKNOWN_DISCONNECTLISTENER_OBJECT;
		return 0;
	}

	public Vector<DwOpenReceiveActionListener> getRecvListeners() {
		return recvListeners;
	}

	public void setRecvListeners(Vector<DwOpenReceiveActionListener> _recvListeners) {
		recvListeners = _recvListeners;
	}

	public Vector<DwOpenSendActionListener> getSendListeners() {
		return sendListeners;
	}

	public void setSendListeners(Vector<DwOpenSendActionListener> _sendListeners) {
		sendListeners = _sendListeners;
	}

	public Vector<DwOpenDisconnectActionListener> getDisconnectListeners() {
		return disconnectListeners;
	}

	public void setDisconnectListeners(Vector<DwOpenDisconnectActionListener> _disconnectListeners) {
		disconnectListeners = _disconnectListeners;
	}

	public Vector<DwOpenMailboxEventListener> getMailboxListeners() {
		return mailboxListeners;
	}

	public void setMailboxListeners(Vector<DwOpenMailboxEventListener> _mailboxListeners) {
		mailboxListeners = _mailboxListeners;
	}

}
