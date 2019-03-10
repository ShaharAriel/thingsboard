package com.devicewise.tr50.admin.api;

import java.io.IOException;

import com.devicewise.tr50.admin.api.response.user.DwOpenUserFind;
import com.devicewise.tr50.admin.api.response.user.DwOpenUserList;
import com.devicewise.tr50.admin.api.response.user.DwOpenUserOrgList;
import com.devicewise.tr50.admin.api.response.user.DwOpenUserOrgTagCloud;
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

public class DwOpenUser extends DwOpenGeneric{
	
	public DwOpenUser(DwClient client){
		super(client);
	}
	
	public int create(String emailAddress, String password, String defaultOrgId, String firstName, String lastName, String company, String title, String officePhone, String mobilePhone, boolean isSuperAdmin, boolean isSuperOps, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("emailAddress",emailAddress,"password",password,"defaultOrgId",defaultOrgId,"firstName",firstName,"lastName",lastName,"company",company,"title",title,"officePhone",officePhone,"mobilePhone",mobilePhone);
		cmd.addBooleanParam("isSuperAdmin", isSuperAdmin);
		cmd.addBooleanParam("isSuperOps", isSuperOps);
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
	
	public int create(String emailAddress, String password, String defaultOrgId, String firstName, String lastName, String company, String title, String officePhone, String mobilePhone, boolean isSuperAdmin, boolean isSuperOps, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("emailAddress",emailAddress,"password",password,"defaultOrgId",defaultOrgId,"firstName",firstName,"lastName",lastName,"company",company,"title",title,"officePhone",officePhone,"mobilePhone",mobilePhone);
		cmd.addBooleanParam("isSuperAdmin", isSuperAdmin);
		cmd.addBooleanParam("isSuperOps", isSuperOps);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,reply));
		
	}
	
	public int delete(String id, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_DELETE);
		cmd.addStringParam("id",id);
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
	
	public int delete(String id, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_DELETE);
		cmd.addStringParam("id",id);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,reply));
		
	}
	
	public int find(String id, boolean isEmailAddress, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_FIND);
		cmd.addStringParam(isEmailAddress?"id":"emailAddress",id);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenUserFind)
				((DwOpenUserFind)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		
			}
		
		return ret;
		
	}
	
	public int find(String id, boolean isEmailAddress, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenUserFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_FIND);
		cmd.addStringParam(isEmailAddress?"id":"emailAddress",id);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int list(Integer offset, Integer limit, Boolean showAll, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		if(showAll!=null)
			cmd.addBooleanParam("showAll",showAll);

		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenUserList)
				((DwOpenUserList)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int list(Integer offset, Integer limit, Boolean showAll, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenUserList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		if(showAll!=null)
			cmd.addBooleanParam("showAll",showAll);

		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int orgAdd(String userId, Boolean isOrgAdmin, String[] roles, String orgId, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_ADD);
		cmd.addParamsFromStringArrayNameValuePairs("userId",userId,"orgId",orgId);
		if(isOrgAdmin!=null)
			cmd.addBooleanParam("isOrgAdmin",isOrgAdmin);
		if(roles!=null)
			cmd.addStringArrayParam("roles", roles);

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
	
	public int orgAdd(String userId, Boolean isOrgAdmin, String[] roles, String orgId, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_ADD);
		cmd.addParamsFromStringArrayNameValuePairs("userId",userId,"orgId",orgId);
		if(isOrgAdmin!=null)
			cmd.addBooleanParam("isOrgAdmin",isOrgAdmin);
		if(roles!=null)
			cmd.addStringArrayParam("roles", roles);

		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}

	public int orgList(String userId, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_LIST);
		cmd.addStringParam("userId",userId);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenUserOrgList)
				((DwOpenUserOrgList)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int orgList(String userId, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenUserOrgList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_LIST);
		cmd.addStringParam("userId",userId);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int orgRemove(String userId, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_REMOVE);
		cmd.addStringParam("userId",userId);
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
	
	public int orgRemove(String userId, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_REMOVE);
		cmd.addStringParam("userId",userId);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int orgTagCloud(Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_TAG_CLOUD);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(reply instanceof StringReply)
					((StringReply)reply).setValue(packet.getResponse());
			else if(reply instanceof DwOpenResponse)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else if(reply instanceof DwOpenUserOrgTagCloud)
				((DwOpenResponse)reply).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int orgTagCloud(DwOpenReceiveActionListener recv,Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse || reply instanceof DwOpenUserOrgTagCloud))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_TAG_CLOUD);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int orgUpdate(String userId, Boolean isOrgAdmin, String[] roles, String orgId, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_UPDATE);
		cmd.addParamsFromStringArrayNameValuePairs("userId",userId,"orgId",orgId);
		if(isOrgAdmin!=null)
			cmd.addBooleanParam("isOrgAdmin",isOrgAdmin);
		if(roles!=null)
			cmd.addStringArrayParam("roles", roles);

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
	
	public int orgUpdate(String userId, Boolean isOrgAdmin, String[] roles, String orgId, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_ORG_UPDATE);
		cmd.addParamsFromStringArrayNameValuePairs("userId",userId,"orgId",orgId);
		if(isOrgAdmin!=null)
			cmd.addBooleanParam("isOrgAdmin",isOrgAdmin);
		if(roles!=null)
			cmd.addStringArrayParam("roles", roles);

		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,reply));
	
	}
	
	public int update(String id, String emailAddress, String password, String defaultOrgId, String firstName, String lastName, String company, String title, String officePhone, String mobilePhone, boolean isSuperAdmin, boolean isSuperOps, Object reply) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("id",id,"emailAddress",emailAddress,"password",password,"defaultOrgId",defaultOrgId,"firstName",firstName,"lastName",lastName,"company",company,"title",title,"officePhone",officePhone,"mobilePhone",mobilePhone);
		cmd.addBooleanParam("isSuperAdmin", isSuperAdmin);
		cmd.addBooleanParam("isSuperOps", isSuperOps);
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
	
	public int update(String id, String emailAddress, String password, String defaultOrgId, String firstName, String lastName, String company, String title, String officePhone, String mobilePhone, boolean isSuperAdmin, boolean isSuperOps, DwOpenReceiveActionListener recv, Object reply) throws IOException, DwOpenException{
		
		if(reply==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(reply instanceof StringReply || reply instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USER_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("id",id,"emailAddress",emailAddress,"password",password,"defaultOrgId",defaultOrgId,"firstName",firstName,"lastName",lastName,"company",company,"title",title,"officePhone",officePhone,"mobilePhone",mobilePhone);
		cmd.addBooleanParam("isSuperAdmin", isSuperAdmin);
		cmd.addBooleanParam("isSuperOps", isSuperOps);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,reply));
		
	}

}
