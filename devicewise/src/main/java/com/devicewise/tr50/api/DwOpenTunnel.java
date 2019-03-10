package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.codehaus.jackson.JsonGenerationException;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.tunnel.DwOpenTunnelConnHistory;
import com.devicewise.tr50.api.response.tunnel.DwOpenTunnelFind;
import com.devicewise.tr50.api.response.tunnel.DwOpenTunnelHistory;
import com.devicewise.tr50.api.response.tunnel.DwOpenTunnelRouterList;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenTunnel extends DwOpenGeneric{
	
	public DwOpenTunnel(DwClient client){
		super(client);
	}
	
	public int connHistory(Boolean showAll, String state, String thingKey, String tunId, Integer offset, Integer limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_CONN_HISTORY);
		if(showAll!=null)
			cmd.addBooleanParam("showAll", showAll);
		cmd.addStringParam("state", state);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("tunId", tunId);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenTunnelConnHistory)
				((DwOpenTunnelConnHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int connHistory(Boolean showAll, String state, String thingKey, String tunId, Integer offset, Integer limit, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenTunnelConnHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_CONN_HISTORY);
		if(showAll!=null)
			cmd.addBooleanParam("showAll", showAll);
			cmd.addStringParam("state", state);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("tunId", tunId);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int history(Boolean showAll, String state, String thingKey, String[] ids, String sessionId, Integer offset, Integer limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_HISTORY);
		if(showAll!=null)
			cmd.addBooleanParam("showAll", showAll);
		cmd.addStringParam("state", state);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("sessionId", sessionId);
		cmd.addStringArrayParam("ids", ids);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenTunnelHistory)
				((DwOpenTunnelHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int history(Boolean showAll, String state, String thingKey, String sessionId, String[] ids, Integer offset, Integer limit, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenTunnelHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_HISTORY);
		if(showAll!=null)
			cmd.addBooleanParam("showAll", showAll);
		cmd.addStringParam("state", state);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("sessionId", sessionId);
		cmd.addStringArrayParam("ids", ids);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int open(String thingKey, String key, LinkedHashMap<String,Integer> latencies, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();

		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_OPEN);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("key", key);
		try{	
			if(latencies!=null)
				cmd.addObjectParam("latencies", latencies);
			packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}

		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenTunnelFind)
				((DwOpenTunnelFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int open(String thingKey, String key, LinkedHashMap<String,Integer> latencies, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenTunnelFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();

		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_OPEN);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("key", key);
		try{	
			if(latencies!=null)
				cmd.addObjectParam("latencies", latencies);
			packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
	
		return(client.sendPacket(packet,recv,response));
	}

	public int routerList(Boolean showDetails, Boolean showOnline, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null) {
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		}
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_ROUTER_LIST);
		if(showDetails!=null)
			cmd.addBooleanParam("showDetails",showDetails);
		if(showOnline!=null)
			cmd.addBooleanParam("showOnline",showOnline);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenTunnelRouterList)
				((DwOpenTunnelRouterList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int routerList(Boolean showDetails, Boolean showOnline, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenTunnelRouterList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_ROUTER_LIST);
		if(showDetails!=null)
			cmd.addBooleanParam("showDetails",showDetails);
		if(showOnline!=null)
			cmd.addBooleanParam("showOnline",showOnline);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

	public int close(String tunId, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_CLOSE);
		cmd.addStringParam("tunId",tunId);
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
	
	public int close(String tunId, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_TUNNEL_CLOSE);
		cmd.addStringParam("tunId",tunId);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
}
