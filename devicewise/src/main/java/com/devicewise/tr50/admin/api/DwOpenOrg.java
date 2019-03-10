package com.devicewise.tr50.admin.api;

import java.io.IOException;

import com.devicewise.tr50.admin.api.response.org.DwOpenOrgFind;
import com.devicewise.tr50.admin.api.response.org.DwOpenOrgList;
import com.devicewise.tr50.api.DwOpenGeneric;
import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenOrg extends DwOpenGeneric{
	
	public DwOpenOrg(DwClient client){
		super(client);
	}
	
	public int create(String key, String name, String type, String desc, String locale, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",key,"name",name,"type",type,"desc",desc,"locale",locale);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
				((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenOrgFind)
				((DwOpenOrgFind)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
	}
	
	public int create(String key, String name, String type, String desc, String locale, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenOrgFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",key,"name",name,"type",type,"desc",desc,"locale",locale);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,reply));
		
	}
	
	public int delete(String id, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_DELETE);
		cmd.addStringParam("id",id);
		packet.addCommand(DwOpenCommands.CORR_ID_DO,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int delete(String id, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_DELETE);
		cmd.addStringParam("id",id);
		packet.addCommand(DwOpenCommands.CORR_ID_DO,cmd);
		
		return(client.sendPacket(packet,recv,reply));
		
	}
	
	public int find(String key, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_FIND);
		cmd.addStringParam("key",key);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenOrgFind)
				((DwOpenOrgFind)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		
			}
		
		return ret;
		
	}
	
	public int find(String key, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenOrgFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_FIND);
		cmd.addStringParam("key",key);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int list(Integer offset, Integer limit, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);

		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenOrgList)
				((DwOpenOrgList)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int list(Integer offset, Integer limit, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenOrgList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int update(String key, String name, String type, String desc, String locale, String owner, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_UPDATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",key,"name",name,"type",type,"desc",desc,"locale",locale,"owner",owner);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
				((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
	}
	
	public int update(String key, String name, String type, String desc, String locale, String owner, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ORG_UPDATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",key,"name",name,"type",type,"desc",desc,"locale",locale,"owner",owner);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,reply));
		
	}

}
