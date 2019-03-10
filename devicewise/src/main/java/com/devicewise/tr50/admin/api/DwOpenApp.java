package com.devicewise.tr50.admin.api;

import java.io.IOException;

import com.devicewise.tr50.admin.api.response.app.DwOpenAppFind;
import com.devicewise.tr50.admin.api.response.app.DwOpenAppList;
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

public class DwOpenApp extends DwOpenGeneric{
	
	public DwOpenApp(DwClient client){
		super(client);
	}
	
	public int create(String name, String desc, String token, Boolean autoRegThingDefId, String[] autoRegTags, String[] autoRegSecTags, String[] roles, Boolean isSuperAdmin, Boolean isSuperOps, Boolean isOrgAdmin, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"desc",desc,"token",token);
		if(autoRegThingDefId!=null)
			cmd.addBooleanParam("autoRegThingDefId", autoRegThingDefId);
		if(autoRegTags!=null)
			cmd.addStringArrayParam("autoRegTags", autoRegTags);
		if(autoRegSecTags!=null)
			cmd.addStringArrayParam("autoRegSecTags", autoRegSecTags);
		if(roles!=null)
			cmd.addStringArrayParam("roles", roles);
		if(isSuperAdmin!=null)
			cmd.addBooleanParam("isSuperAdmin", isSuperAdmin);
		if(isSuperOps!=null)
			cmd.addBooleanParam("isSuperOps", isSuperOps);
		if(isSuperOps!=null)
			cmd.addBooleanParam("isOrgAdmin", isOrgAdmin);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
				((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenAppFind)
				((DwOpenAppFind)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
	}
	
	public int create(String name, String desc, String token, Boolean autoRegThingDefId, String[] autoRegTags, String[] autoRegSecTags, String[] roles, Boolean isSuperAdmin, Boolean isSuperOps, Boolean isOrgAdmin, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenAppFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"desc",desc,"token",token);
		if(autoRegThingDefId!=null)
			cmd.addBooleanParam("autoRegThingDefId", autoRegThingDefId);
		if(autoRegTags!=null)
			cmd.addStringArrayParam("autoRegTags", autoRegTags);
		if(autoRegSecTags!=null)
			cmd.addStringArrayParam("autoRegSecTags", autoRegSecTags);
		if(roles!=null)
			cmd.addStringArrayParam("roles", roles);
		if(isSuperAdmin!=null)
			cmd.addBooleanParam("isSuperAdmin", isSuperAdmin);
		if(isSuperOps!=null)
			cmd.addBooleanParam("isSuperOps", isSuperOps);
		if(isSuperOps!=null)
			cmd.addBooleanParam("isOrgAdmin", isOrgAdmin);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);		
		return(client.sendPacket(packet,recv,reply));
		
	}
	
	public int delete(String id, boolean isName, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_DELETE);
		cmd.addStringParam(isName?"name":"id",id);
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
	
	public int delete(String id, boolean isName, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_DELETE);
		cmd.addStringParam(isName?"name":"id",id);
		packet.addCommand(DwOpenCommands.CORR_ID_DO,cmd);
		
		return(client.sendPacket(packet,recv,reply));
		
	}
	
	public int find(String id, boolean isName, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_FIND);
		cmd.addStringParam(isName?"name":"id",id);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenAppFind)
				((DwOpenAppFind)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		
			}
		
		return ret;
		
	}
	
	public int find(String id, boolean isName, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenAppFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_FIND);
		cmd.addStringParam(isName?"name":"id",id);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int find(String token, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_FIND);
		cmd.addStringParam("token",token);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenAppFind)
				((DwOpenAppFind)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		
			}
		
		return ret;
		
	}
	
	public int find(String token, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenAppFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_FIND);
		cmd.addStringParam("token",token);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int list(Integer offset, Integer limit, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_LIST);
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
			else if(reply instanceof DwOpenAppList)
				((DwOpenAppList)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int list(Integer offset, Integer limit, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenAppList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int update(String id, boolean isName, String desc, String token, Boolean autoRegThingDefId, String[] autoRegTags, String[] autoRegSecTags, String[] roles, Boolean isSuperAdmin, Boolean isSuperOps, Boolean isOrgAdmin, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_UPDATE);
		cmd.addStringParam(isName?"name":"id",id);
		cmd.addParamsFromStringArrayNameValuePairs("desc",desc,"token",token);
		if(autoRegThingDefId!=null)
			cmd.addBooleanParam("autoRegThingDefId", autoRegThingDefId);
		if(autoRegTags!=null)
			cmd.addStringArrayParam("autoRegTags", autoRegTags);
		if(autoRegSecTags!=null)
			cmd.addStringArrayParam("autoRegSecTags", autoRegSecTags);
		if(roles!=null)
			cmd.addStringArrayParam("roles", roles);
		if(isSuperAdmin!=null)
			cmd.addBooleanParam("isSuperAdmin", isSuperAdmin);
		if(isSuperOps!=null)
			cmd.addBooleanParam("isSuperOps", isSuperOps);
		if(isSuperOps!=null)
			cmd.addBooleanParam("isOrgAdmin", isOrgAdmin);
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
	
	public int update(String id, boolean isName, String desc, String token, Boolean autoRegThingDefId, String[] autoRegTags, String[] autoRegSecTags, String[] roles, Boolean isSuperAdmin, Boolean isSuperOps, Boolean isOrgAdmin, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_APP_UPDATE);
		cmd.addStringParam(isName?"name":"id",id);
		cmd.addParamsFromStringArrayNameValuePairs("desc",desc,"token",token);
		if(autoRegThingDefId!=null)
			cmd.addBooleanParam("autoRegThingDefId", autoRegThingDefId);
		if(autoRegTags!=null)
			cmd.addStringArrayParam("autoRegTags", autoRegTags);
		if(autoRegSecTags!=null)
			cmd.addStringArrayParam("autoRegSecTags", autoRegSecTags);
		if(roles!=null)
			cmd.addStringArrayParam("roles", roles);
		if(isSuperAdmin!=null)
			cmd.addBooleanParam("isSuperAdmin", isSuperAdmin);
		if(isSuperOps!=null)
			cmd.addBooleanParam("isSuperOps", isSuperOps);
		if(isSuperOps!=null)
			cmd.addBooleanParam("isOrgAdmin", isOrgAdmin);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);		
		
		return(client.sendPacket(packet,recv,reply));
		
	}
}
