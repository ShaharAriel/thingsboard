package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Vector;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.role.DwOpenRoleFind;
import com.devicewise.tr50.api.response.role.DwOpenRoleList;
import com.devicewise.tr50.api.response.role.DwOpenRolePerms;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenRole extends DwOpenGeneric{
	
	public DwOpenRole(DwClient client){
		super(client);
	}

	public int create(String key, String name, String desc, String[] viewTags, String[] updateTags, String[] perms, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",key,"name",name,"desc",desc);
		if(viewTags!=null)
			cmd.addStringArrayParam("viewTags",viewTags);
		if(updateTags!=null)
			cmd.addStringArrayParam("updateTags",updateTags);
		if(perms!=null)
			cmd.addStringArrayParam("perms", perms);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenRoleFind)
				((DwOpenRoleFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int create(String key, String name, String desc, String[] viewTags, String[] updateTags, String[] perms, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenRoleFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",key,"name",name,"desc",desc);
		if(viewTags!=null)
			cmd.addStringArrayParam("viewTags",viewTags);
		if(updateTags!=null)
			cmd.addStringArrayParam("updateTags",updateTags);
		if(perms!=null)
			cmd.addStringArrayParam("perms", perms);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int create(String key,String name, String desc, Vector<String> viewTags, Vector<String> updateTags, Vector<String> perms, Object response) throws IOException, DwOpenException{
		return(create(key,name, desc, viewTags!=null?viewTags.toArray(new String[viewTags.size()]):null,updateTags!=null?updateTags.toArray(new String[updateTags.size()]):null,perms!=null?perms.toArray(new String[perms.size()]):null,response));
	}
	
	public int create(String key,String name, String desc, Vector<String> roTags, Vector<String> rwTags, Vector<String> perms, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		return(create(key,name, desc, roTags!=null?roTags.toArray(new String[roTags.size()]):null,rwTags!=null?rwTags.toArray(new String[rwTags.size()]):null,rwTags!=null?perms.toArray(new String[perms.size()]):null,recv,response));
	}

	public int delete(String Key, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_DELETE);
		cmd.addStringParam("key",Key);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
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
	
	public int delete(String Key, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_DELETE);
		cmd.addStringParam("key",Key);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int find(String Key, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_FIND);
		cmd.addStringParam("key",Key);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenRoleFind)
				((DwOpenRoleFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int find(String Key, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenRoleFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_FIND);
		cmd.addStringParam("key",Key);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int list(Integer offset, Integer limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenRoleList)
				((DwOpenRoleList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int list(Integer offset, Integer limit, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenRoleList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int perms(Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_PERMS);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenRolePerms)
				((DwOpenRolePerms)response).parseResponse(packet.getResponse());
			else if(response instanceof LinkedHashMap<?,?>)
				((DwOpenRolePerms)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int perms(DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenRolePerms))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_PERMS);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int update(String Key, String name, String desc, String[] viewTags, String[] updateTags, String[] perms, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_UPDATE);
		cmd.addStringParam("key", Key);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"desc",desc);
		if(viewTags!=null)
			cmd.addStringArrayParam("viewTags",viewTags);
		if(updateTags!=null)
			cmd.addStringArrayParam("updateTags",updateTags);
		if(perms!=null)
			cmd.addStringArrayParam("perms", perms);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
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
	
	public int update(String Key, String name, String desc, String[] viewTags, String[] updateTags, String[] perms, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_ROLE_UPDATE);
		cmd.addStringParam("key", Key);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"desc",desc);
		if(viewTags!=null)
			cmd.addStringArrayParam("viewTags",viewTags);
		if(updateTags!=null)
			cmd.addStringArrayParam("updateTags",updateTags);
		if(perms!=null)
			cmd.addStringArrayParam("perms", perms);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int update(String Key, String name, String desc, Vector<String> roTags, Vector<String> rwTags, Vector<String> perms, Object response) throws IOException, DwOpenException{
		return(update(Key, name, desc, roTags!=null?roTags.toArray(new String[roTags.size()]):null,rwTags!=null?rwTags.toArray(new String[rwTags.size()]):null,perms!=null?perms.toArray(new String[perms.size()]):null,response));
	}
	
	public int update(String Key, String name, String desc, Vector<String> roTags, Vector<String> rwTags, Vector<String> perms, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		return(update(Key, name, desc, roTags!=null?roTags.toArray(new String[roTags.size()]):null,rwTags!=null?rwTags.toArray(new String[rwTags.size()]):null,perms!=null?perms.toArray(new String[perms.size()]):null,recv,response));
	}
}
