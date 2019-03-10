package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.codehaus.jackson.JsonGenerationException;

import com.devicewise.tr50.api.params.DwOpenThingAttribute;
import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.thing.DwOpenThingAttributeGet;
import com.devicewise.tr50.api.response.thing.DwOpenThingFind;
import com.devicewise.tr50.api.response.thing.DwOpenThingList;
import com.devicewise.tr50.api.response.thing.DwOpenThingTagCloud;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenThing extends DwOpenGeneric{
	
	public DwOpenThing(DwClient client){
		super(client);
	}
	
	public int attributeGet(String thingKey, String attrKey, Object response)throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_ATTR_GET);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("key",attrKey);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenThingAttributeGet)
				((DwOpenThingAttributeGet)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int attributeGet(String thingKey, String attrKey, DwOpenReceiveActionListener recv, Object response)throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingAttributeGet))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_ATTR_GET);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("key",attrKey);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

	public int attributeSet(String thingKey, String attrKey, String attrValue, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_ATTR_SET);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("key",attrKey);
		cmd.addStringParam("value",attrValue);
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
	
	public int attributeSet(String thingKey, String attrKey, String attrValue, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_ATTR_SET);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("key",attrKey);
		cmd.addStringParam("value",attrValue);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int attributeUnset(String thingKey, String attrKey, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_ATTR_UNSET);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("key",attrKey);
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
	
	public int attributeUnset(String thingKey, String attrKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_ATTR_UNSET);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("key",attrKey);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int bind(String key, String autoDefKey, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_BIND);
		cmd.addStringParam("key",key);
		cmd.addStringParam("autoDefKey",autoDefKey);
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
	
	public int bind(String key, String autoDefKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_BIND);
		cmd.addStringParam("key",key);
		cmd.addStringParam("autoDefKey",autoDefKey);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int create(String name, String key, String defKey, String desc, String iccid, String esn, String tunnelActualHost, String tunnelVirtualHost, LinkedHashMap<String,Integer> tunnelLatencies, String[]tags, String[] secTags, Boolean locEnabled, Object response, DwOpenThingAttribute ... attrs) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"key",key,"defKey",defKey,"desc",desc,"iccid",iccid,"esn",esn,"tunnelActualHost",tunnelActualHost,"tunnelVirtualHost",tunnelVirtualHost);
		if(locEnabled!=null)
			cmd.addBooleanParam("locEnabled",locEnabled);
		
		try{
			if(tunnelLatencies!=null)
				cmd.addObjectParam("tunnelLatencies", tunnelLatencies);
			if(secTags!=null)
				cmd.addStringArrayParam("secTags", secTags);
			if(tags!=null)
				cmd.addStringArrayParam("tags", tags);
			if(attrs!=null && attrs.length!=0)
				cmd.addObjectArrayParamWithKey("attrs",attrs);
			
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
			else if(response instanceof DwOpenThingFind)
				((DwOpenThingFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int create(String name, String key, String defKey, String desc, String iccid, String esn, String tunnelActualHost, String tunnelVirtualHost, LinkedHashMap<String,Integer> tunnelLatencies, String[]tags, String[] secTags, Boolean locEnabled, DwOpenReceiveActionListener recv, Object response, DwOpenThingAttribute ... attrs) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"key",key,"defKey",defKey,"desc",desc,"iccid",iccid,"esn",esn,"tunnelActualHost",tunnelActualHost,"tunnelVirtualHost",tunnelVirtualHost);
		if(locEnabled!=null)
			cmd.addBooleanParam("locEnabled",locEnabled);
		
		try{
			if(tunnelLatencies!=null)
				cmd.addObjectParam("tunnelLatencies", tunnelLatencies);
			if(secTags!=null)
				cmd.addStringArrayParam("secTags", secTags);
			if(tags!=null)
				cmd.addStringArrayParam("tags", tags);
			if(attrs!=null && attrs.length!=0)
				cmd.addObjectArrayParamWithKey("attrs",attrs);
		
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
			
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
		
		
		return(client.sendPacket(packet,recv,response));

	}
	
	public int create(String name, String key, String defKey, String desc, String iccid, String esn, String tunnelActualHost, String tunnelVirtualHost, LinkedHashMap<String,Integer> tunnelLatencies, String[]tags, String[] secTags,  Vector<DwOpenThingAttribute> attrs,Boolean locEnabled, Object response) throws IOException, DwOpenException{
		return(create(name, key, defKey,  desc, iccid, esn, tunnelActualHost, tunnelVirtualHost, tunnelLatencies, tags, secTags, locEnabled, response,attrs!=null?attrs.toArray(new DwOpenThingAttribute[attrs.size()]):null));
	}
	
	public int create(String name, String key, String defKey, String desc, String iccid, String esn, String tunnelActualHost, String tunnelVirtualHost, LinkedHashMap<String,Integer> tunnelLatencies, String[]tags, String[] secTags,  Vector<DwOpenThingAttribute> attrs, Boolean locEnabled, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(create(name, key, defKey,  desc, iccid, esn, tunnelActualHost, tunnelVirtualHost, tunnelLatencies, tags, secTags, locEnabled, recv, response,attrs!=null?attrs.toArray(new DwOpenThingAttribute[attrs.size()]):null));
	}

	public int delete(String Key, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_DELETE);
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
	
	public int delete(String Key, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_DELETE);
		cmd.addStringParam("key",Key);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int find(String thingKey, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_FIND);
		cmd.addStringParam("key",thingKey);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenThingFind)
				((DwOpenThingFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int find(String thingKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_FIND);
		cmd.addStringParam("key",thingKey);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));

	}
	
	public int list(Integer offset, Integer limit, String sort, String[] keys, String[] tags, String[] show, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		if(sort!=null)
			cmd.addStringParam("sort",sort);
		try{
			if(tags!=null && tags.length!=0)
				cmd.addStringArrayParam("tags", tags);
			if(keys!=null && keys.length!=0)
				cmd.addStringArrayParam("keys", keys);
			if(show!=null && show.length!=0)
				cmd.addStringArrayParam("show", show);
			packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
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
			else if(response instanceof DwOpenThingList)
				((DwOpenThingList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int list(Integer offset, Integer limit, String sort, String[] keys, String[] tags, String[] show, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_LIST);
		
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		if(sort!=null)
			cmd.addStringParam("sort",sort);
		try{
			if(tags!=null && tags.length!=0)
				cmd.addStringArrayParam("tags", tags);
			if(keys!=null && keys.length!=0)
				cmd.addStringArrayParam("keys", keys);
			if(show!=null && show.length!=0)
				cmd.addStringArrayParam("show", show);
			packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}
		
		
		return(client.sendPacket(packet,recv,response));
	
	}
	
	public int sectagAdd(String thingKey, Object response, String...sectags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_ADD);
		cmd.addStringParam("thingKey",thingKey);
		try{
			if(sectags!=null && sectags.length!=0)
				cmd.addStringArrayParam("secTags",(String[])sectags);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
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
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int sectagAdd(String thingKey, DwOpenReceiveActionListener recv, Object response, String...sectags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_ADD);
		cmd.addStringParam("thingKey",thingKey);
		try{
			if(sectags!=null && sectags.length!=0)
				cmd.addStringArrayParam("secTags",(String[])sectags);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int sectagAdd(String[] thingKey, Object response, String...sectags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_ADD);
		try{
			cmd.addStringArrayParam("thingKey",thingKey);
			if(sectags!=null && sectags.length!=0)
				cmd.addStringArrayParam("secTags",(String[])sectags);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
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
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int sectagAdd(String[] thingKey, DwOpenReceiveActionListener recv, Object response, String...sectags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_ADD);
		try{
			if(thingKey!=null)
				cmd.addStringArrayParam("thingKey",thingKey);
			if(sectags!=null && sectags.length!=0)
				cmd.addStringArrayParam("secTags",(String[])sectags);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int sectagAdd(String thingKey, Vector<String> sectags, Object response) throws IOException, DwOpenException{
		return(sectagAdd(thingKey, response, sectags!=null?sectags.toArray(new String[sectags.size()]):null));
	}
	
	public int sectagAdd(String thingKey, Vector<String> sectags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(sectagAdd(thingKey, recv, response, sectags!=null?sectags.toArray(new String[sectags.size()]):null));
	}
	
	public int sectagAdd(String[] thingKey,  Vector<String> sectags, Object response) throws IOException, DwOpenException{
		return(sectagAdd(thingKey, response, sectags!=null?sectags.toArray(new String[sectags.size()]):null));
	}
	
	public int sectagAdd(String[] thingKey,  Vector<String> sectags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(sectagAdd(thingKey, recv, response, sectags!=null?sectags.toArray(new String[sectags.size()]):null));
	}
	
	public int sectagCloud(String[] tags, String[] keys, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_CLOUD);
		if(tags!=null)
			cmd.addStringArrayParam("secTags",tags);
		if(keys!=null)
			cmd.addStringArrayParam("keys",keys);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenThingTagCloud)
				((DwOpenThingTagCloud)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int sectagCloud(String[] tags, String[] keys,DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingTagCloud))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_CLOUD);
		if(tags!=null && tags.length!=0)
			cmd.addStringArrayParam("secTags",(String[])tags);
		if(keys!=null && keys.length!=0)
			cmd.addStringArrayParam("keys",(String[])keys);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int sectagCloud(Vector<String> tags, Vector<String> keys, Object response) throws IOException, DwOpenException{
		return(sectagCloud(tags!=null?tags.toArray(new String[tags.size()]):null, keys!=null?keys.toArray(new String[keys.size()]):null, response));
	}
	
	public int sectagCloud(Vector<String> tags, Vector<String> keys, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(sectagCloud(tags!=null?tags.toArray(new String[tags.size()]):null, keys!=null?keys.toArray(new String[keys.size()]):null, recv, response));
	}

	
	public int sectagDelete(String thingKey, Object response, String...sectags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_DELETE);
		cmd.addStringParam("thingKey",thingKey);
		if(sectags!=null && sectags.length!=0)
			cmd.addStringArrayParam("secTags",(String[])sectags);
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
	
	public int sectagDelete(String thingKey, DwOpenReceiveActionListener recv,Object response, String...sectags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_DELETE);
		cmd.addStringParam("thingKey",thingKey);
		if(sectags!=null && sectags.length!=0)
			cmd.addStringArrayParam("secTags",(String[])sectags);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int sectagDelete(String []thingKey, Object response, String...sectags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_DELETE);

		if(thingKey!=null)
			cmd.addStringArrayParam("thingKey",thingKey);
		if(sectags!=null && sectags.length!=0)
			cmd.addStringArrayParam("secTags",(String[])sectags);
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
	
	public int sectagDelete(String []thingKey,  DwOpenReceiveActionListener recv, Object response, String...sectags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_SECTAG_DELETE);

		if(thingKey!=null)
			cmd.addStringArrayParam("thingKey",thingKey);
		if(sectags!=null && sectags.length!=0)
			cmd.addStringArrayParam("secTags",(String[])sectags);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int sectagDelete(String thingKey, Vector<String> sectags, Object response) throws IOException, DwOpenException{
		return(sectagDelete(thingKey, response, sectags!=null?sectags.toArray(new String[sectags.size()]):null));
	}
	
	public int sectagDelete(String thingKey, Vector<String> sectags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(sectagDelete(thingKey, recv,response, sectags!=null?sectags.toArray(new String[sectags.size()]):null));
	}
	
	public int sectagDelete(String []thingKey,  Vector<String> sectags, Object response) throws IOException, DwOpenException{
		return(sectagDelete(thingKey, response, sectags!=null?sectags.toArray(new String[sectags.size()]):null));
	}
	
	public int sectagDelete(String []thingKey, Vector<String> sectags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(sectagDelete(thingKey, recv,response, sectags!=null?sectags.toArray(new String[sectags.size()]):null));
	}
	
	public int tagAdd(String thingKey, Object response, String...tags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_ADD);
		cmd.addStringParam("thingKey",thingKey);
		try{
			if(tags!=null && tags.length!=0)
				cmd.addStringArrayParam("tags",(String[])tags);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
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
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int tagAdd(String thingKey, DwOpenReceiveActionListener recv, Object response, String...tags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_ADD);
		cmd.addStringParam("thingKey",thingKey);
		try{
			if(tags!=null && tags.length!=0)
				cmd.addStringArrayParam("tags",(String[])tags);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
		
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int tagAdd(String[] thingKey,  Object response, String...tags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_ADD);
		try{
			if(thingKey!=null)
				cmd.addStringArrayParam("thingKey",thingKey);
			if(tags!=null && tags.length!=0)
				cmd.addStringArrayParam("tags",(String[])tags);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
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
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int tagAdd(String[] thingKey,  DwOpenReceiveActionListener recv, Object response, String...tags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_ADD);
		try{
			if(thingKey!=null)
				cmd.addStringArrayParam("thingKey",thingKey);
			if(tags!=null && tags.length!=0)
				cmd.addStringArrayParam("tags",(String[])tags);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
	
		return(client.sendPacket(packet,recv,response));
	}
	
	public int tagAdd(String thingKey, Vector<String> tags, Object response) throws IOException, DwOpenException{
		return(tagAdd(thingKey, response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int tagAdd(String thingKey, Vector<String> tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(tagAdd(thingKey, recv, response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int tagAdd(String[] thingKey,  Vector<String> tags, Object response) throws IOException, DwOpenException{
		return(tagAdd(thingKey, response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int tagAdd(String[] thingKey,  Vector<String> tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(tagAdd(thingKey, recv, response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int tagCloud(String[] tags, String[] keys, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_CLOUD);
		if(tags!=null)
			cmd.addStringArrayParam("tags",tags);
		if(keys!=null)
			cmd.addStringArrayParam("keys",keys);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenThingTagCloud)
				((DwOpenThingTagCloud)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int tagCloud(String[] tags, String[] keys,DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenThingTagCloud))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
			
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_CLOUD);
		if(tags!=null && tags.length!=0)
			cmd.addStringArrayParam("tags",(String[])tags);
		if(keys!=null && keys.length!=0)
			cmd.addStringArrayParam("keys",(String[])keys);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int tagCloud(Vector<String> tags, Vector<String> keys, Object response) throws IOException, DwOpenException{
		return(tagCloud(tags!=null?tags.toArray(new String[tags.size()]):null, keys!=null?keys.toArray(new String[keys.size()]):null, response));
	}
	
	public int tagCloud(Vector<String> tags, Vector<String> keys, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(tagCloud(tags!=null?tags.toArray(new String[tags.size()]):null, keys!=null?keys.toArray(new String[keys.size()]):null, recv, response));
	}
	
	public int tagDelete(String thingKey, Object response, String...tags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_DELETE);
		cmd.addStringParam("thingKey",thingKey);
		if(tags!=null && tags.length!=0)
			cmd.addStringArrayParam("tags",(String[])tags);
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
	
	public int tagDelete(String thingKey, DwOpenReceiveActionListener recv,Object response, String...tags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_DELETE);
		cmd.addStringParam("thingKey",thingKey);
		if(tags!=null && tags.length!=0)
			cmd.addStringArrayParam("tags",(String[])tags);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int tagDelete(String []thingKey,  Object response, String...tags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_DELETE);

		if(thingKey!=null)
			cmd.addStringArrayParam("thingKey",thingKey);
		if(tags!=null && tags.length!=0)
			cmd.addStringArrayParam("tags",(String[])tags);
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
	
	public int tagDelete(String []thingKey,  DwOpenReceiveActionListener recv, Object response, String...tags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_TAG_DELETE);

		if(thingKey!=null)
			cmd.addStringArrayParam("thingKey",thingKey);
		if(tags!=null && tags.length!=0)
			cmd.addStringArrayParam("tags",(String[])tags);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int tagDelete(String thingKey, Vector<String> tags, Object response) throws IOException, DwOpenException{
		return(tagDelete(thingKey, response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int tagDelete(String thingKey, Vector<String> tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(tagDelete(thingKey, recv,response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int tagDelete(String []thingKey,  Vector<String> tags, Object response) throws IOException, DwOpenException{
		return(tagDelete(thingKey, response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int tagDelete(String []thingKey, Vector<String> tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(tagDelete(thingKey, recv,response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	
	public int unbind(String key, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_UNBIND);
		cmd.addStringParam("key",key);
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
	
	public int unbind(String key, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_UNBIND);
		cmd.addStringParam("key",key);
		packet.addCommand(DwOpenCommands.CORR_ID_CMD,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int update(String Key, DwOpenThingFind newThing, Object response, String...unset) throws IOException, DwOpenException{
		if(newThing==null)
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
		return(update(Key,newThing.getName(),newThing.getDesc(),newThing.getIccid(),newThing.getEsn(),newThing.getTunnelActualHost(),newThing.getTunnelVirtualHost(),newThing.getTunnelLatencies(),newThing.isLocEnabled(),newThing.getTags(),newThing.getSecTags(),unset,response));
	}
	
	public int update(String Key, DwOpenThingFind newThing, DwOpenReceiveActionListener recv,Object response, String...unset) throws IOException, DwOpenException{
		if(newThing==null)
			return DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT;
		return(update(Key,newThing.getName(),newThing.getDesc(),newThing.getIccid(),newThing.getEsn(),newThing.getTunnelActualHost(),newThing.getTunnelVirtualHost(),newThing.getTunnelLatencies(),newThing.isLocEnabled(),newThing.getTags(),newThing.getSecTags(),unset,recv,response));
	}
	
	public int update(String Key, String name, String desc, String iccid, String esn, String tunnelActualHost, String tunnelVirtualHost, LinkedHashMap<String,Integer> tunnelLatencies, Boolean locEnabled, String[] tags, String[]secTags, String[] unset, Object response) throws IOException, DwOpenException{

		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_UPDATE);
		cmd.addStringParam("key",Key);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"desc",desc,"iccid",iccid,"esn",esn,"tunnelActualHost",tunnelActualHost,"tunnelVirtualHost",tunnelVirtualHost);
		try{
			if(tunnelLatencies!=null)
				cmd.addObjectParam("tunnelLatencies", tunnelLatencies);
			if(locEnabled!=null)
				cmd.addBooleanParam("locEnabled", locEnabled);
			if(tags!=null)
				cmd.addStringArrayParam("tags", tags);
			if(secTags!=null)
				cmd.addStringArrayParam("secTags", secTags);
			if(unset!=null)
				cmd.addStringArrayParam("unset", unset);
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
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int update(String Key, String name, String desc, String iccid, String esn, String tunnelActualHost, String tunnelVirtualHost, LinkedHashMap<String,Integer> tunnelLatencies, Boolean locEnabled, String[] tags, String[]secTags, String[] unset, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_THING_UPDATE);
		cmd.addStringParam("key",Key);
		cmd.addParamsFromStringArrayNameValuePairs("name",name,"desc",desc,"iccid",iccid,"esn",esn,"tunnelActualHost",tunnelActualHost,"tunnelVirtualHost",tunnelVirtualHost);
		try{
			if(tunnelLatencies!=null)
				cmd.addObjectParam("tunnelLatencies", tunnelLatencies);
			if(locEnabled!=null)
				cmd.addBooleanParam("locEnabled", locEnabled);
			if(tags!=null)
				cmd.addStringArrayParam("tags", tags);
			if(secTags!=null)
				cmd.addStringArrayParam("secTags", secTags);
			if(unset!=null)
				cmd.addStringArrayParam("unset", unset);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int unset(String Key, Object response, String...unsetFields) throws IOException, DwOpenException{
		return(update(Key,  null, null, null, null, null, null, null, null, null, null, unsetFields, response));
	}
	
	public int unset(String Key, DwOpenReceiveActionListener recv, Object response, String...unsetFields) throws IOException, DwOpenException{
		return(update(Key, null, null, null, null, null, null, null, null, null, null, unsetFields, recv, response));
	}
}
