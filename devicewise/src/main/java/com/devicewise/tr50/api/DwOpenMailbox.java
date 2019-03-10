package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxCheck;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxFind;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxList;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxPurge;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxSend;
import com.devicewise.tr50.api.response.mailbox.DwOpenMailboxSummary;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenMailbox extends DwOpenGeneric{
	
	public DwOpenMailbox(DwClient client){
		super(client);
	}

	public int ack(String id, Integer errorCode, String errorMessage, LinkedHashMap<String,Object> params, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_ACK);
		cmd.addParamsFromStringArrayNameValuePairs("id",id,"errorMessage",errorMessage);
		if(params!=null && params.size()!=0)
			cmd.addObjectParam("params", params);
		if(errorCode!=null)
			cmd.addIntegerParam("errorCode",errorCode);
		
		packet.addCommand(DwOpenCommands.CORR_ID_ACK,cmd);
		
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
	
	public int ack(String id, Integer errorCode, String errorMessage, LinkedHashMap<String,Object> params, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_ACK);
		cmd.addParamsFromStringArrayNameValuePairs("id",id,"errorMessage",errorMessage);
		if(params!=null && params.size()!=0)
			cmd.addObjectParam("params", params);
		if(errorCode!=null)
			cmd.addIntegerParam("errorCode",errorCode);
		
		packet.addCommand(DwOpenCommands.CORR_ID_ACK,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

	public int check(Boolean autoComplete, Integer limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_CHECK);
		if(autoComplete!=null)
			cmd.addBooleanParam("autoComplete",autoComplete);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);
		
		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenMailboxCheck)
				((DwOpenMailboxCheck)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int check(Boolean autoComplete, Integer limit, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenMailboxCheck))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_CHECK);
		if(autoComplete!=null)
			cmd.addBooleanParam("autoComplete",autoComplete);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int list(String thingKey, Integer offset, Integer limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_LIST);
		cmd.addStringParam("thingKey", thingKey);

		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);
		
		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenMailboxList)
				((DwOpenMailboxList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int list(String thingKey, Integer offset, Integer limit, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenMailboxList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();

		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_LIST);
		cmd.addStringParam("thingKey", thingKey);

		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int find(String id, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_FIND);
		cmd.addStringParam("id", id);
	
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		ret=client.sendPacket(packet);
		
		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenMailboxFind)
				((DwOpenMailboxFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int find(String id, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenMailboxFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();

		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_FIND);
		cmd.addStringParam("id", id);
		
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int purge(String thingKey, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_PURGE);
		cmd.addStringParam("thingKey", thingKey);
	
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		ret=client.sendPacket(packet);
		
		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenMailboxPurge)
				((DwOpenMailboxPurge)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int purge(String thingKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenMailboxPurge))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();

		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_PURGE);
		cmd.addStringParam("thingKey", thingKey);
		
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int send(String thingKey, String command, LinkedHashMap<String,Object> params, Integer ackTimeout, Integer ttl,Boolean singleton, String sessionId, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_SEND);
		
		cmd.addStringParam("thingKey", thingKey);
		cmd.addStringParam("command", command);
		if(params!=null)
			cmd.addObjectParam("params", params);
		
		if(singleton!=null)
			cmd.addBooleanParam("singleton", singleton);
		if(ackTimeout!=null)
			cmd.addIntegerParam("ackTimeout", ackTimeout);
		if(ttl!=null)
			cmd.addIntegerParam("ttl", ttl);	
		cmd.addStringParam("sessionId", sessionId);
		
		packet.addCommand(DwOpenCommands.CORR_ID_EXEC,cmd);
		
		ret=client.sendPacket(packet);
		
		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenMailboxSend)
				((DwOpenMailboxSend)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int send(String thingKey, String command, LinkedHashMap<String,Object> params, Integer ackTimeout, Integer ttl, Boolean singleton, String sessionId, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenMailboxSend))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_SEND);
		
		cmd.addStringParam("thingKey", thingKey);
		cmd.addStringParam("command", command);
		
		if(params!=null)
			cmd.addObjectParam("params", params);

		if(singleton!=null)
			cmd.addBooleanParam("singleton", singleton);
		if(ackTimeout!=null)
			cmd.addIntegerParam("ackTimeout", ackTimeout);
		if(ttl!=null)
			cmd.addIntegerParam("ttl", ttl);	
		cmd.addStringParam("sessionId", sessionId);
				
		packet.addCommand(DwOpenCommands.CORR_ID_EXEC,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int send(UUID sessionId,String command, LinkedHashMap<String,Object> params, Integer ackTimeout, Integer ttl, Boolean singleton, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_SEND);
		
		cmd.addStringParam("sessionId", sessionId.toString());
		cmd.addStringParam("command", command);
		cmd.addObjectParam("params", params);

		if(singleton!=null)
			cmd.addBooleanParam("singleton", singleton);
		if(ackTimeout!=null)
			cmd.addIntegerParam("ackTimeout", ackTimeout);
		if(ttl!=null)
			cmd.addIntegerParam("ttl", ttl);	
		
		packet.addCommand(DwOpenCommands.CORR_ID_EXEC,cmd);
		
		ret=client.sendPacket(packet);
		
		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenMailboxSend)
				((DwOpenMailboxSend)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int send(UUID sessionId, String command, LinkedHashMap<String,Object> params, Integer ttl, Integer ackTimeout, Boolean singleton, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenMailboxSend))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_SEND);
		
		cmd.addStringParam("sessionId", sessionId.toString());
		cmd.addStringParam("command", command);
		cmd.addObjectParam("params", params);
		if(singleton!=null)
			cmd.addBooleanParam("singleton", singleton);
		if(ackTimeout!=null)
			cmd.addIntegerParam("ackTimeout", ackTimeout);
		if(ttl!=null)
			cmd.addIntegerParam("ttl", ttl);	
		
		packet.addCommand(DwOpenCommands.CORR_ID_EXEC,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int summary(String thingKey, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_SUMMARY);
		
		cmd.addStringParam("thingKey", thingKey);
		
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		ret=client.sendPacket(packet);
		
		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
				((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenMailboxSummary)
				((DwOpenMailboxSummary)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int summary(String thingKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenMailboxSummary))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_SUMMARY);
		
		cmd.addStringParam("thingKey", thingKey);
		
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int update(String id, String msg, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_UPDATE);
		
		cmd.addStringParam("id", id);
		cmd.addStringParam("msg", msg);
		
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
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
	
	public int update(String id, String msg, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_MAILBOX_UPDATE);
		
		cmd.addStringParam("id", id);
		cmd.addStringParam("msg", msg);
		
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
}
