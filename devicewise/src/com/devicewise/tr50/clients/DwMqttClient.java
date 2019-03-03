package com.devicewise.tr50.clients;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.constants.MqttConstants;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwOpenMailboxWorker;
import com.devicewise.tr50.protocol.DwQueuedPacket;

public class DwMqttClient extends DwClient implements MqttCallback{

	private MqttConnectOptions connOpts;
	private MqttClient client;
	private ExecutorService threadPool =  Executors.newCachedThreadPool();
	private String brokerURL;
	private String user;
	private String password;
	private String clientID;


	public DwMqttClient() throws IOException, MqttException{
		super();
	}

	public void initialize(String host, boolean isSSL, String clientID) throws MqttException{

		setBrokerURL((isSSL?"ssl://":"tcp://")+host+":"+(isSSL?MqttConstants.MQTT_SSL_PORT:MqttConstants.MQTT_TCP_PORT));
		setClient(new MqttClient(this.getBrokerURL(),clientID,null));
		setClientID(clientID);
		if(client!=null)
			client.setCallback(this);
	}

	public void initialize(String host, String port, boolean isSSL, String clientID) throws MqttException{

		setBrokerURL((isSSL?"ssl://":"tcp://")+host+":"+port);
		setClient(new MqttClient(getBrokerURL(),clientID,null));
		setClientID(clientID);
		if(client!=null)
			client.setCallback(this);

	}

	public int authenticate(String user, String password){

		connOpts = new MqttConnectOptions();
		connOpts.setKeepAliveInterval(MqttConstants.DEFAULT_KEEPALIVE);
		connOpts.setUserName(user);
		connOpts.setPassword(password.toCharArray());
		return(connect());
	}


	@Override
	public int sendJSONRequest(int msgId,String json, StringReply response) throws DwOpenException{

		String pubTopic = new String("api/"+msgId);

		MqttMessage message;
		message = new MqttMessage(json.getBytes());
		message.setQos(MqttConstants.QOS_AT_LEAST_ONCE);

		try {

			if(!isConnected()){
				if(connect()!=0)
					return DwOpenErrors.PROTOCOL_CLIENT_NOT_CONNECTED;
			}
			synchronized(queue.get(msgId)){
				this.getClient().publish(pubTopic, message);
				if(DwOpenCommands.CMD_TIMEOUT_MILLIS<0)
					queue.get(msgId).wait();
				else
					queue.get(msgId).wait(DwOpenCommands.CMD_TIMEOUT_MILLIS);
			}

		} catch (MqttException e) {
			// TODO Auto-generated catch block
			throw new DwOpenException(e.getReasonCode(),e.getLocalizedMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(!queue.containsKey(msgId)){
			return DwOpenErrors.PROTOCOL_CLIENT_NOT_CONNECTED;
		}

		if(queue.get(msgId).getException()==null && queue.get(msgId).getResponse()==null){
			return DwOpenErrors.PROTOCOL_SEND_TIMEOUT;
		}

		if((queue.get(msgId).getException()!=null)){
			throw new DwOpenException(queue.get(msgId).getException());
		}

		response.setValue(((StringReply)queue.get(msgId).getResponse()).getValue());

		return 0;

	}

	@Override
	public int sendAsyncJSONRequest(int msgId, String json) throws DwOpenException {

		String pubTopic = new String("api/"+msgId);

		MqttMessage message;
		message = new MqttMessage(json.getBytes());
		message.setQos(MqttConstants.QOS_AT_LEAST_ONCE);

		if(!isConnected()){
			if(connect()!=0)
				return DwOpenErrors.PROTOCOL_CLIENT_NOT_CONNECTED;
		}

		try {
			this.getClient().publish(pubTopic, message.getPayload(),MqttConstants.QOS_AT_LEAST_ONCE,false);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			throw new DwOpenException(e.getReasonCode(),e.getLocalizedMessage());
		}
		return 0;
	}

	@Override
	public int connect() {
		try {
			if(client!=null){
				if(!isConnected())
					getClient().connect(connOpts);
				else
					return 0;
			}
			else
				return(DwOpenErrors.PROTOCOL_CLIENT_NOT_INITIALIZED);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return(DwOpenErrors.PROTOCOL_CLIENT_AUTHENTICATION_FAILED);
		}

		return 0;
	}

	public int disconnect(){

		try {
			threadPool.shutdownNow();
			if(client!=null){
				if(client.isConnected())
					this.getClient().disconnect();
				else
					return(DwOpenErrors.PROTOCOL_CLIENT_NOT_CONNECTED);
			}
			else
				return(DwOpenErrors.PROTOCOL_CLIENT_NOT_INITIALIZED);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return(e.getReasonCode());
		}
		return 0;
	}

	public boolean isConnected(){
		return(getClient().isConnected());
	}

	@Override
	public void connectionLost(Throwable cause) {
		synchronized(queue){
			
			onDisconnect(this,cause);

			while(queue.keySet().iterator().hasNext()){
				DwQueuedPacket pkt = queue.get(queue.keySet().iterator().next());
				synchronized(pkt){
					pkt.notify();
				}
			}

			queue.clear();
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

		if(token.getException()!=null){
			for(String topic:token.getTopics()){
				synchronized(queue){
					DwQueuedPacket pkt = queue.get(Integer.parseInt(topic.replaceFirst("reply/", "")));

					if(pkt!=null)
						pkt.setException(token.getException());

					addQueue(pkt.getMsgId(),pkt);

					if(pkt.getRecv()==null){
						synchronized(pkt){
							pkt.notify();
						}	
					}
				}	
			}
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage reply){

		if(topic.indexOf("notify/mailbox_activity")!=-1){
			DwOpenMailboxWorker mailboxWorker = new DwOpenMailboxWorker(this);
			threadPool.submit(mailboxWorker);
			return;
		}

		Integer msgId = -1;
		DwQueuedPacket pkt;

		try {
			msgId = Integer.parseInt(topic.replaceFirst("reply/", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			onReceive(DwOpenErrors.API_REPLY_NOT_FOUND,e,null,null);

		}

		if(queue.containsKey(msgId)){
			pkt	= queue.get(msgId);

			if(pkt.getRecv()==null)
			{
				StringReply response = new StringReply();
				response.setValue(new String(reply.getPayload()));

				synchronized(queue){
					pkt.setResponse(response);
					addQueue(pkt.getMsgId(),pkt);
				}

				synchronized(pkt){
					pkt.notify();
				}

			}

			else{
				synchronized(queue){
					removeQueue(msgId);
				}
				try {
					if(pkt.getResponse() instanceof DwOpenGenericResponse)
						((DwOpenGenericResponse)pkt.getResponse()).parseResponse(new String(reply.getPayload()));
					else if (pkt.getResponse() instanceof StringReply)
						((StringReply)pkt.getResponse()).setValue(new String(reply.getPayload()));

					pkt.getRecv().onReceive(this,0,null,pkt.getPacket(),pkt.getResponse());

				} catch (IOException e) {
					pkt.getRecv().onReceive(this,DwOpenErrors.API_REPLY_PARSE_ERROR,e,pkt.getPacket(),pkt.getResponse());

				} catch (DwOpenException e) {
					// TODO Auto-generated catch block
					pkt.getRecv().onReceive(this,DwOpenErrors.API_REPLY_ERROR,e,pkt.getPacket(),pkt.getResponse());

				}
			}
		}
		else{
			onReceive(DwOpenErrors.API_REPLY_NOT_FOUND,null,null,null);
		}

	}


	public String getBrokerURL() {
		return brokerURL;
	}

	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public MqttClient getClient() {
		return client;
	}

	public void setClient(MqttClient client) {
		this.client = client;
	}

}
