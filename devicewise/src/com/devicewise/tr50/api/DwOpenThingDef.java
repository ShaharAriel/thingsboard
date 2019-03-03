package com.devicewise.tr50.api;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;

import com.devicewise.tr50.api.params.DwOpenAlarmParam;
import com.devicewise.tr50.api.params.DwOpenThingDefAttribute;
import com.devicewise.tr50.api.params.DwOpenThingDefMethod;
import com.devicewise.tr50.api.params.DwOpenThingDefProperty;
import com.devicewise.tr50.api.params.DwOpenThingDefTunnel;
import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.thingdef.DwOpenThingDefFind;
import com.devicewise.tr50.api.response.thingdef.DwOpenThingDefList;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenThingDef extends DwOpenGeneric{
	
	public DwOpenThingDef(DwClient client){
		super(client);
	}
	
	public int create(String name, String key,  Boolean autoDefProps, Boolean autoDefAttrs, DwOpenThingDefAttribute[] attributes, DwOpenAlarmParam[] alarms, DwOpenThingDefProperty[] props, DwOpenThingDefMethod[] methods, DwOpenThingDefTunnel[] tunnels, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"key",key);
		if(autoDefProps!=null)
			cmd.addBooleanParam("autoDefProps",autoDefProps);
		if(autoDefAttrs!=null)
			cmd.addBooleanParam("autoDefAttrs",autoDefAttrs);
		
		try{
			if(attributes!=null && attributes.length!=0)
				cmd.addObjectArrayParamWithKey("attributes", attributes);
			if(props!=null && props.length!=0)
				cmd.addObjectArrayParamWithKey("properties", props);
			if(methods!=null && methods.length!=0)
				cmd.addObjectArrayParamWithKey("methods", methods);
			if(alarms!=null && alarms.length!=0)
				cmd.addObjectArrayParamWithKey("alarms", alarms);
			if(tunnels!=null && tunnels.length!=0)
				cmd.addObjectArrayParamWithKey("tunnels", tunnels);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
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
			else if(response instanceof DwOpenThingDefFind)
				((DwOpenThingDefFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int create(String name, String key, Boolean autoDefProps, Boolean autoDefAttrs, DwOpenThingDefAttribute[] attributes, DwOpenAlarmParam[] alarms, DwOpenThingDefProperty[] props, DwOpenThingDefMethod[] methods, DwOpenThingDefTunnel[] tunnels, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingDefFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"key",key);
		if(autoDefProps!=null)
			cmd.addBooleanParam("autoDefProps",autoDefProps);
		if(autoDefAttrs!=null)
			cmd.addBooleanParam("autoDefAttrs",autoDefAttrs);
		
		try{
			if(attributes!=null && attributes.length!=0)
				cmd.addObjectArrayParamWithKey("attributes", attributes);
			if(props!=null && props.length!=0)
				cmd.addObjectArrayParamWithKey("properties", props);
			if(methods!=null && methods.length!=0)
				cmd.addObjectArrayParamWithKey("methods", methods);
			if(alarms!=null)
				cmd.addObjectArrayParamWithKey("alarms", alarms);
			if(tunnels!=null)
				cmd.addObjectArrayParamWithKey("tunnels", tunnels);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int delete(String Key, String[]alarms, String[] attributes, String[] methods, String[] properties, String[] tunnels, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_DELETE);
		cmd.addStringParam("key",Key);
		if(alarms!=null)
			cmd.addStringArrayParam("alarms", alarms);
		if(attributes!=null)
			cmd.addStringArrayParam("attributes", attributes);
		if(methods!=null)
			cmd.addStringArrayParam("methods", methods);
		if(properties!=null)
			cmd.addStringArrayParam("properties", properties);
		if(tunnels!=null)
			cmd.addStringArrayParam("tunnels", tunnels);
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
	
	
	public int delete(String Key, String[]alarms, String[] attributes, String[] methods, String[] properties, String[] tunnels, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_DELETE);
		cmd.addStringParam("key",Key);
		if(alarms!=null)
			cmd.addStringArrayParam("alarms", alarms);
		if(attributes!=null)
			cmd.addStringArrayParam("attributes", attributes);
		if(methods!=null)
			cmd.addStringArrayParam("methods", methods);
		if(properties!=null)
			cmd.addStringArrayParam("properties", properties);
		if(tunnels!=null)
			cmd.addStringArrayParam("tunnels", tunnels);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int find(String thingDefKey, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_FIND);
		cmd.addStringParam("key",thingDefKey);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenThingDefFind)
				((DwOpenThingDefFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int find(String thingDefKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingDefFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_FIND);
		cmd.addStringParam("key",thingDefKey);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));

	}
	
	public int list(Integer offset, Integer limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_LIST);
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
			else if(response instanceof DwOpenThingDefList)
				((DwOpenThingDefList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int list(Integer offset, Integer limit, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingDefList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int update(String Key, String name, Boolean autoDefProps, Boolean autoDefAttrs, DwOpenThingDefAttribute[] attributes, DwOpenAlarmParam[] alarms, DwOpenThingDefProperty[] props, DwOpenThingDefMethod[] methods, DwOpenThingDefTunnel[] tunnels, Object response, String...unset) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_UPDATE);
		cmd.addStringParam("key",Key);
		cmd.addStringParam("name",name);
		if(autoDefProps!=null)
			cmd.addBooleanParam("autoDefProps",autoDefProps);
		if(autoDefAttrs!=null)
			cmd.addBooleanParam("autoDefAttrs",autoDefAttrs);
		
		try{
			if(unset!=null && unset.length!=0)
				cmd.addStringArrayParam("unset",unset);
			if(attributes!=null && attributes.length!=0)
				cmd.addObjectArrayParamWithKey("attributes", attributes);
			if(props!=null && props.length!=0)
				cmd.addObjectArrayParamWithKey("properties", props);
			if(methods!=null && methods.length!=0)
				cmd.addObjectArrayParamWithKey("methods", methods);
			if(alarms!=null && alarms.length!=0)
				cmd.addObjectArrayParamWithKey("alarms", alarms);
			if(tunnels!=null && tunnels.length!=0)
				cmd.addObjectArrayParamWithKey("tunnels", tunnels);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
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
			else if(response instanceof DwOpenThingDefFind)
				((DwOpenThingDefFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int update(String Key, String name, Boolean autoDefProps, Boolean autoDefAttrs, DwOpenThingDefAttribute[] attributes, DwOpenAlarmParam[] alarms, DwOpenThingDefProperty[] props, DwOpenThingDefMethod[] methods, DwOpenThingDefTunnel[] tunnels, DwOpenReceiveActionListener recv, Object response, String...unset) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingDefFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THINGDEF_UPDATE);
		cmd.addStringParam("key",Key);
		cmd.addStringParam("name",name);
		if(autoDefProps!=null)
			cmd.addBooleanParam("autoDefProps",autoDefProps);
		if(autoDefAttrs!=null)
			cmd.addBooleanParam("autoDefAttrs",autoDefAttrs);
		
		try{
			if(unset!=null && unset.length!=0)
				cmd.addStringArrayParam("unset",unset);
			if(attributes!=null && attributes.length!=0)
				cmd.addObjectArrayParamWithKey("attributes", attributes);
			if(props!=null && props.length!=0)
				cmd.addObjectArrayParamWithKey("properties", props);
			if(methods!=null && methods.length!=0)
				cmd.addObjectArrayParamWithKey("methods", methods);
			if(alarms!=null && alarms.length!=0)
				cmd.addObjectArrayParamWithKey("alarms", alarms);
			if(tunnels!=null && tunnels.length!=0)
				cmd.addObjectArrayParamWithKey("tunnels", tunnels);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
		return(client.sendPacket(packet,recv,response));
	}
	
	public int unset(String Key, Object response, String...unsetFields) throws IOException, DwOpenException{
		return(update(Key,  null,null,null, null,null, null, null, null, response, unsetFields));
	}
	
	public int unset(String Key, DwOpenReceiveActionListener recv, Object response, String...unsetFields) throws IOException, DwOpenException{
		return(update(Key,  null,null,null, null, null, null, null, null, recv, response, unsetFields));
	}

}
