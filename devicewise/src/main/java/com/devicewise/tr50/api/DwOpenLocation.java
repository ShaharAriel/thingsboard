package com.devicewise.tr50.api;

import java.io.IOException;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.location.DwOpenLocationEncodeDecode;
import com.devicewise.tr50.api.response.location.DwOpenLocationHistory;
import com.devicewise.tr50.api.response.location.DwOpenLocationStats;
import com.devicewise.tr50.api.response.location.DwOpenLocationWeather;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenLocation extends DwOpenGeneric{
	
	public DwOpenLocation(DwClient client){
		super(client);
	}
	
	public int decode(double latitude,double longitude, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenLocationEncodeDecode))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_DECODE);
		cmd.addDoubleParam("lat",latitude);
		cmd.addDoubleParam("lng", longitude);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

	public int decode(double latitude,double longitude, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_DECODE);
		cmd.addDoubleParam("lat",latitude);
		cmd.addDoubleParam("lng", longitude);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenLocationEncodeDecode)
				((DwOpenLocationEncodeDecode)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}

	public int encode(String location, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_ENCODE);
		cmd.addParamsFromStringArrayNameValuePairs("location",location);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenLocationEncodeDecode)
				((DwOpenLocationEncodeDecode)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int encode(String location, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenLocationEncodeDecode))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_ENCODE);
		cmd.addParamsFromStringArrayNameValuePairs("location",location);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int history(String thingKey, String start, String end, boolean showAddress, int offset, int limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_HISTORY);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end);
		cmd.addBooleanParam("showAddress",showAddress);
		if(offset!=-1)
			cmd.addIntegerParam("offset", offset);
		if(limit!=-1)
			cmd.addIntegerParam("limit", offset);

		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenLocationHistory)
				((DwOpenLocationHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int history(String thingKey, String start, String end, boolean showAddress, int offset, int limit, DwOpenReceiveActionListener recv,Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenLocationHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_HISTORY);
		cmd.addStringParam("thingKey",thingKey);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end);
		cmd.addBooleanParam("showAddress",showAddress);
		if(offset!=-1)
			cmd.addIntegerParam("offset", offset);
		if(limit!=-1)
			cmd.addIntegerParam("limit", offset);

		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int history(String thingKey, String last, boolean showAddress, int offset, int limit, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_HISTORY);

		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"last",last);
		cmd.addBooleanParam("showAddress",showAddress);
		if(offset!=-1)
			cmd.addIntegerParam("offset", offset);
		if(limit!=-1)
			cmd.addIntegerParam("limit", offset);

		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenLocationHistory)
				((DwOpenLocationHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}

		return ret;
		
	}
	
	public int history(String thingKey, String last, boolean showAddress, int offset, int limit, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenLocationHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
	
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_HISTORY);

		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"last",last);
		cmd.addBooleanParam("showAddress",showAddress);
		if(offset!=-1)
			cmd.addIntegerParam("offset", offset);
		if(limit!=-1)
			cmd.addIntegerParam("limit", offset);

		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}

	public int publish(String thingKey, String timestamp, String corrId, Double lat, Double lng,  Double heading, Double altitude, Double speed, Double fixAcc, String fixType, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_PUBLISH);
		
		cmd.addStringParam("thingKey",thingKey);
		cmd.addStringParam("ts",timestamp);
		cmd.addStringParam("corrId", corrId);
		if(lat!=null)
			cmd.addDoubleParam("lat", lat);
		if(lng!=null)
			cmd.addDoubleParam("lng", lng);
		if(heading!=null)
			cmd.addDoubleParam("heading", heading);
		if(altitude!=null)
			cmd.addDoubleParam("altitude", altitude);
		if(speed!=null)
			cmd.addDoubleParam("speed", speed);
		if(fixAcc!=null)
			cmd.addDoubleParam("fixAcc", fixAcc);
		cmd.addStringParam("fixType", fixType);

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
	
	public int publish(String thingKey, String timestamp, String corrId, Double lat, Double lng,  Double heading, Double altitude, Double speed, Double fixAcc, String fixType, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
	
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_PUBLISH);
		
		cmd.addStringParam("thingKey",thingKey);

		cmd.addStringParam("ts",timestamp);
		cmd.addStringParam("corrId", corrId);
		if(lat!=null)
			cmd.addDoubleParam("lat", lat);
		if(lng!=null)
			cmd.addDoubleParam("lng", lng);
		if(heading!=null)
			cmd.addDoubleParam("heading", heading);
		if(altitude!=null)
			cmd.addDoubleParam("altitude", altitude);
		if(speed!=null)
			cmd.addDoubleParam("speed", speed);
		if(fixAcc!=null)
			cmd.addDoubleParam("fixAcc", fixAcc);
		cmd.addStringParam("fixType", fixType);

		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}

	public int stats(String date, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_STATS);
		cmd.addParamsFromStringArrayNameValuePairs("date",date);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenLocationStats)
				((DwOpenLocationStats)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int stats(String date, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenLocationStats))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
	
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_STATS);
		cmd.addParamsFromStringArrayNameValuePairs("date",date);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}

	
	public int weather(double latitude,double longitude, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_WEATHER);
		cmd.addDoubleParam("lat",latitude);
		cmd.addDoubleParam("lng", longitude);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenLocationWeather)
				((DwOpenLocationWeather)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int weather(double latitude,double longitude, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenLocationWeather))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
	
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOCATION_WEATHER);
		cmd.addDoubleParam("lat",latitude);
		cmd.addDoubleParam("lng", longitude);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
}
