package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.geofence.DwOpenGeofenceFind;
import com.devicewise.tr50.api.response.geofence.DwOpenGeofenceHistory;
import com.devicewise.tr50.api.response.geofence.DwOpenGeofenceList;
import com.devicewise.tr50.api.response.geofence.DwOpenGeofenceLocate;
import com.devicewise.tr50.api.response.geofence.DwOpenGeofenceTagCloud;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenGeofence extends DwOpenGeneric{
	
	public DwOpenGeofence(DwClient client){
		super(client);
	}

	public int create(String key, String name, ArrayList<double[]> points, Double radius, String[] tags, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",key,"name",name);
		if(points!=null)
			cmd.addObjectParam("points", points);
		if(radius!=null)
			cmd.addDoubleParam("radius", radius);
		if(tags!=null)
		cmd.addStringArrayParam("tags", tags);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenGeofenceFind)
				((DwOpenGeofenceFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int create(String key, String name, ArrayList<double[]> points, Double radius, String[] tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenGeofenceFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_CREATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",key,"name",name);
		if(points!=null)
			cmd.addObjectParam("points", points);
		if(radius!=null)
			cmd.addDoubleParam("radius", radius);
		if(tags!=null)
		cmd.addStringArrayParam("tags", tags);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int delete(String Key, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_DELETE);
		cmd.addStringParam("key",Key);
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
	
	public int delete(String Key, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_DELETE);
		cmd.addStringParam("key",Key);
		packet.addCommand(DwOpenCommands.CORR_ID_DO,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int find(String Key, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_FIND);
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
			else if(response instanceof DwOpenGeofenceFind)
				((DwOpenGeofenceFind)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int find(String Key, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenGeofenceFind))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_FIND);
		cmd.addStringParam("key",Key);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

	public int history(String geofenceKey, String thingKey, String last, Integer offset, Integer limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_HISTORY);
		cmd.addStringParam("geofenceKey",geofenceKey);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("last", last);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
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
			else if(response instanceof DwOpenGeofenceHistory)
				((DwOpenGeofenceHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int history(String geofenceKey, String thingKey, String last, Integer offset, Integer limit, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenGeofenceHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_HISTORY);
		cmd.addStringParam("geofenceKey",geofenceKey);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("last", last);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int history(String geofenceKey, String thingKey, String start, String end, Integer offset, Integer limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_HISTORY);
		cmd.addStringParam("geofenceKey",geofenceKey);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
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
			else if(response instanceof DwOpenGeofenceHistory)
				((DwOpenGeofenceHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int history(String geofenceKey, String thingKey, String start, String end, Integer offset, Integer limit, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenGeofenceHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_HISTORY);
		cmd.addStringParam("geofenceKey",geofenceKey);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

	public int list(Integer offset, Integer limit, String[] tags, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_LIST);
		
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", offset);
		if(tags!=null)
			cmd.addStringArrayParam("tags",tags);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenGeofenceList)
				((DwOpenGeofenceList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int list(Integer offset, Integer limit, String[] tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenGeofenceList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_LIST);
		
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", offset);
		if(tags!=null)
			cmd.addStringArrayParam("tags",tags);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int locate(double[] point, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_LOCATE);
		
		cmd.addDoubleArrayParam("point", point);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenGeofenceLocate)
				((DwOpenGeofenceLocate)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int locate(double[] point, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenGeofenceLocate))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_LOCATE);
		
		cmd.addDoubleArrayParam("point", point);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int tagCloud(String[] tags, String[] ids, boolean areKeys, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_TAG_CLOUD);
		
		cmd.addStringArrayParam("tags", tags);
		cmd.addStringArrayParam(areKeys?"ids":"keys", ids);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenGeofenceTagCloud)
				((DwOpenGeofenceTagCloud)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int tagCloud(String[] tags, String[] ids, boolean areKeys, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenGeofenceTagCloud))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_TAG_CLOUD);
		
		cmd.addStringArrayParam("tags", tags);
		cmd.addStringArrayParam(areKeys?"ids":"keys", ids);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int update(String Key, String name, ArrayList<double[]> points, Double radius, String[] tags, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_UPDATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",Key,"name",name);
		if(points!=null)
			cmd.addObjectParam("points", points);
		if(radius!=null)
			cmd.addDoubleParam("radius", radius);
		if(tags!=null)
		cmd.addStringArrayParam("tags", tags);
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
	
	public int update(String Key, String name, ArrayList<double[]> points, Double radius, String[] tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GEOFENCE_UPDATE);
		cmd.addParamsFromStringArrayNameValuePairs("key",Key,"name",name);
		if(points!=null)
			cmd.addObjectParam("points", points);
		if(radius!=null)
			cmd.addDoubleParam("radius", radius);
		if(tags!=null)
		cmd.addStringArrayParam("tags", tags);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
}
