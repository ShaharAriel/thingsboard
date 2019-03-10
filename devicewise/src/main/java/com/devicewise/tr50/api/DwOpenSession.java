package com.devicewise.tr50.api;

import java.io.IOException;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.session.DwOpenSessionFind;
import com.devicewise.tr50.api.response.session.DwOpenSessionInfo;
import com.devicewise.tr50.api.response.session.DwOpenSessionList;
import com.devicewise.tr50.api.response.session.DwOpenSessionOrgList;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenSession extends DwOpenGeneric{

	public DwOpenSession(DwClient client){
		super(client);
	}

	public int find(String id, Object response) throws IOException, DwOpenException{

		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_FIND);
		cmd.addStringParam("id", id);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);

		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);

			if(response instanceof StringReply)
				((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenSessionFind)
				((DwOpenSessionFind)response).parseResponse(packet.getResponse());
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

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenSessionFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_FIND);
		cmd.addStringParam("id", id);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);

		return(client.sendPacket(packet,recv,response));
	}


	public int info(Object response) throws IOException, DwOpenException{

		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_INFO);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);

		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);

			if(response instanceof StringReply)
				((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenSessionInfo)
				((DwOpenSessionInfo)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenSessionFind)
				((DwOpenSessionFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}

		return ret;
	}

	public int info(DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenSessionInfo || response instanceof DwOpenSessionFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_INFO);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);

		return(client.sendPacket(packet,recv,response));
	}

	public int list(Object response) throws IOException, DwOpenException{

		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_LIST);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);

		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);

			if(response instanceof StringReply)
				((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenSessionList)
				((DwOpenSessionList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}

		return ret;
	}

	public int list(DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenSessionList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_LIST);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);

		return(client.sendPacket(packet,recv,response));
	}

	public int orgList(Object response) throws IOException, DwOpenException{

		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_ORG_LIST);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);

		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);

			if(response instanceof StringReply)
				((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenSessionOrgList)
				((DwOpenSessionOrgList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}

		return ret;
	}

	public int orgList(DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenSessionOrgList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_ORG_LIST);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);

		return(client.sendPacket(packet,recv,response));
	}

	public int orgSwitch(String orgKey, Object response) throws IOException, DwOpenException{

		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_ORG_SWITCH);
		cmd.addStringParam("key",orgKey);
		packet.addCommand(DwOpenCommands.CORR_ID_DO,cmd);

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

	public int orgSwitch(String orgKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_SESSION_ORG_SWITCH);
		cmd.addStringParam("key",orgKey);
		packet.addCommand(DwOpenCommands.CORR_ID_DO,cmd);

		return(client.sendPacket(packet,recv,response));
	}
}
