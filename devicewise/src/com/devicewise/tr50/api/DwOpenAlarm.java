package com.devicewise.tr50.api;

import java.io.IOException;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.alarm.DwOpenAlarmHistory;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenAlarm extends DwOpenGeneric{
	
	public DwOpenAlarm(DwClient client){
		super(client);
	}
	
		public int history(String thingKey, String key, String start, String end, Boolean split, Object response) throws IOException, DwOpenException{
			
			int ret=0;
			
			if(response==null)
				return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
			DwPacket packet = new DwPacket(client);
			DwCommand cmd= new DwCommand();
			cmd.setCommand(DwOpenCommands.CMD_API_ALARM_HISTORY);
			cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",key,"start",start,"end",end);
			if(split!=null)
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
				else if(response instanceof DwOpenAlarmHistory)
					((DwOpenAlarmHistory)response).parseResponse(packet.getResponse());
				else
					return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
			
			return ret;
		}
		
		public int history(String thingKey, String key, String start, String end, Boolean split, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
			
			if(response==null)
				return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
	
			if(recv==null)
				return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
			
			if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenAlarmHistory))
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
			
			DwPacket packet = new DwPacket(client);
			DwCommand cmd= new DwCommand();
			cmd.setCommand(DwOpenCommands.CMD_API_ALARM_HISTORY);
			cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",key,"start",start,"end",end);
			if(split!=null)
				cmd.addBooleanParam("split", split);
			packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
			
			return(client.sendPacket(packet,recv,response));
			
		}
		
		public int history(String thingKey, String key, String last, Boolean split, Object response) throws IOException, DwOpenException{
			
			int ret=0;
			if(response==null)
				return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
			DwPacket packet = new DwPacket(client);
			DwCommand cmd= new DwCommand();
			cmd.setCommand(DwOpenCommands.CMD_API_ALARM_HISTORY);
			cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",key,"last",last);
			if(split!=null)
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
				else if(response instanceof DwOpenAlarmHistory)
					((DwOpenAlarmHistory)response).parseResponse(packet.getResponse());
				else
					return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
			
			return ret;
			
		}
		
		public int history(String thingKey, String key, String last, Boolean split, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
			
			if(response==null)
				return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
	
			if(recv==null)
				return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
			
			if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenAlarmHistory))
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
			
			DwPacket packet = new DwPacket(client);
			DwCommand cmd= new DwCommand();
			cmd.setCommand(DwOpenCommands.CMD_API_ALARM_HISTORY);
			cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"key",key,"last",last);
			if(split!=null)
				cmd.addBooleanParam("split", split);
			packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
			
			return(client.sendPacket(packet,recv,response));
			
		}
		
		public int publish(String thingKey, String timestamp, String corrId, String key, int state, String message, Object response) throws IOException, DwOpenException{
			
			int ret=0;
			if(response==null)
				return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
			
			DwPacket packet = new DwPacket(client);
			DwCommand cmd= new DwCommand();
			cmd.setCommand(DwOpenCommands.CMD_API_ALARM_PUBLISH);
			cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"ts",timestamp,"corrId",corrId,"key",key,"msg",message);
			cmd.addIntegerParam("state",state);
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
		
		public int publish(String thingKey, String timestamp, String corrId, String key, int state, String message, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
			
			if(response==null)
				return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
	
			if(recv==null)
				return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
			
			if(!(response instanceof StringReply || response instanceof DwOpenResponse))
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
			
			DwPacket packet = new DwPacket(client);
			DwCommand cmd= new DwCommand();
			cmd.setCommand(DwOpenCommands.CMD_API_ALARM_PUBLISH);
			cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"ts",timestamp,"corrId",corrId,"key",key,"msg",message);
			cmd.addIntegerParam("state",state);
			packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
			
			return(client.sendPacket(packet,recv,response));
		
		}

}
