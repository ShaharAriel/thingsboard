package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.Vector;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenEmail extends DwOpenGeneric{
	
	public DwOpenEmail(DwClient client){
		super(client);
	}

	public int send(String from, String fromName, String subject, String body, Object response, String ... to) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_EMAIL_SEND);
		cmd.addParamsFromStringArrayNameValuePairs("from",from,"fromName",fromName,"subject",subject,"body",body);
		if(to!=null)
			cmd.addStringArrayParam("to",to);
		packet.addCommand(DwOpenCommands.CORR_ID_DATA,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int send(String from, String fromName, String subject, String body, DwOpenReceiveActionListener recv, Object response, String ... to) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_EMAIL_SEND);
		cmd.addParamsFromStringArrayNameValuePairs("from",from,"fromName",fromName,"subject",subject,"body",body);
		if(to!=null)
			cmd.addStringArrayParam("to",to);
		packet.addCommand(DwOpenCommands.CORR_ID_DATA,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int send(String from, String fromName, String[] to, String subject, String body, Object response) throws IOException, DwOpenException{
		return(send(from, fromName, subject,body,response,to));
	}
	
	public int send(String from, String fromName, Vector<String> to, String subject, String body, Object response) throws IOException, DwOpenException{
		return(send(from,fromName,subject,body,response,to!=null?to.toArray(new String[to.size()]):null));
	}
	
	public int send(String from, String fromName,String[] to, String subject, String body,  DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(send(from,fromName,subject,body,recv,response,to));
	}
	
	public int send(String from, String fromName, Vector<String> to, String subject, String body, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(send(from, fromName, subject,body,recv,response,to!=null?to.toArray(new String[to.size()]):null));
	}
}
