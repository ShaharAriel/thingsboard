package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.Vector;

import org.codehaus.jackson.JsonGenerationException;

import com.devicewise.tr50.api.params.DwOpenPropertyParam;
import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.property.DwOpenPropertyAggregate;
import com.devicewise.tr50.api.response.property.DwOpenPropertyAggregateSplit;
import com.devicewise.tr50.api.response.property.DwOpenPropertyCurrent;
import com.devicewise.tr50.api.response.property.DwOpenPropertyStats;
import com.devicewise.tr50.api.response.property.DwOpenPropertyHistory;
import com.devicewise.tr50.api.response.property.DwOpenPropertyHistorySplit;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenProperty extends DwOpenGeneric{
	
	public DwOpenProperty(DwClient client){
		super(client);
	}

	public int aggregate(String thingKey, String propKey, String calc, String series, String start, String end, boolean split, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_AGGREGATE);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",propKey,"calc",calc,"series",series,"start",start,"end",end);
		cmd.addBooleanParam("split", split);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenPropertyAggregate)
				((DwOpenPropertyAggregate)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenPropertyAggregateSplit)
				((DwOpenPropertyAggregateSplit)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int aggregate(String thingKey, String propKey, String calc, String series, String start, String end, boolean split, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

			if(response==null)
				return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

			if(recv==null)
				return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
			
			if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenPropertyAggregate || response instanceof DwOpenPropertyAggregateSplit))
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
			
			DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
			cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_AGGREGATE);
			cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",propKey,"calc",calc,"series",series,"start",start,"end",end);
			cmd.addBooleanParam("split", split);
			packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
			
			return(client.sendPacket(packet,recv,response));
		}
	
	public int batch(String thingKey, Object response,DwOpenPropertyParam ... propertyData) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_BATCH);
		cmd.addStringParam("thingKey",thingKey);
		try{
			
			if(propertyData!=null && propertyData.length!=0)
				cmd.addObjectArrayParam("data",(Object[])propertyData);
			
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
	
	public int batch(String thingKey, DwOpenReceiveActionListener recv, Object response,DwOpenPropertyParam ... propertyData) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_BATCH);
		cmd.addStringParam("thingKey",thingKey);
		try{
			if(propertyData!=null && propertyData.length!=0)
				cmd.addObjectArrayParam("data",(Object[])propertyData);
		
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}
				
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int batch(String thingKey, Vector<DwOpenPropertyParam> propertyData, Object response) throws IOException, DwOpenException{
		return(batch(thingKey, response, propertyData!=null?propertyData.toArray(new DwOpenPropertyParam[propertyData.size()]):null));
	}
	
	public int batch(String thingKey, Vector<DwOpenPropertyParam> propertyData, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(batch(thingKey, recv, response, propertyData!=null?propertyData.toArray(new DwOpenPropertyParam[propertyData.size()]):null));
	}

	public int current(String thingKey, String propKey, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_CURRENT);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",propKey);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenPropertyCurrent)
				((DwOpenPropertyCurrent)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int current(String thingKey, String propKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenPropertyCurrent))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_CURRENT);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",propKey);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int history(String thingKey, String propKey, String start, String end, String last, Boolean split, Integer records, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",propKey,"start",start,"end",end,"last",last);
		if(split!=null)
			cmd.addBooleanParam("split", split);
		if(records!=null)
			cmd.addIntegerParam("records", records);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenPropertyHistory)
				((DwOpenPropertyHistory)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenPropertyHistorySplit)
				((DwOpenPropertyHistorySplit)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int history(String thingKey, String propKey, String start, String end, String last, Boolean split, Integer records,DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenPropertyHistory || response instanceof DwOpenPropertyHistorySplit))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",propKey,"start",start,"end",end,"last",last);
		if(split!=null)
			cmd.addBooleanParam("split", split);
		if(records!=null)
			cmd.addIntegerParam("records", records);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int publish(String thingKey, String timestamp, String propKey, double propValue, String corrId, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_PUBLISH);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"ts",timestamp,"key",propKey,"corrId",corrId);
		cmd.addDoubleParam("value",propValue);
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
	
	public int publish(String thingKey, String timestamp, String propKey, double propValue, String corrId, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_PUBLISH);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"ts",timestamp,"key",propKey,"corrId",corrId);
		cmd.addDoubleParam("value",propValue);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	
	}
	
	public int stats(String thingKey, String propKey, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_STATS);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",propKey);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenPropertyStats)
				((DwOpenPropertyStats)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int stats(String thingKey, String propKey, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenPropertyStats))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_PROPERTY_STATS);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",propKey);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
}
