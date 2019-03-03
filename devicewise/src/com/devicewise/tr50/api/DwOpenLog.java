package com.devicewise.tr50.api;

import java.io.IOException;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.log.DwOpenLogList;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenLog extends DwOpenGeneric{
	
	public DwOpenLog(DwClient client){
		super(client);
	}

	public int list(String thingKey, Integer offset, Integer limit, Integer level, String corrId, String triggerId, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOG_LIST);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"corrId",corrId,"triggerId",triggerId);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		if(level!=null)
			cmd.addIntegerParam("level", level);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenLogList)
				((DwOpenLogList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
	}
	
	public int list(String thingKey, Integer offset, Integer limit, Integer level, String corrId, String triggerId,DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenLogList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOG_LIST);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"corrId",corrId,"triggerId",triggerId);
		if(offset!=null)
			cmd.addIntegerParam("offset", offset);
		if(limit!=null)
			cmd.addIntegerParam("limit", limit);
		if(level!=null)
			cmd.addIntegerParam("level", level);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int publish(String thingKey,  String ts, Integer level, String corrId, String msg, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOG_PUBLISH);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"ts",ts,"corrId",corrId,"msg",msg);
		if(level!=null)
			cmd.addIntegerParam("level", level);

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
	
	public int publish(String thingKey,  String ts, Integer level, String corrId, String msg,DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_LOG_PUBLISH);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"ts",ts,"corrId",corrId,"msg",msg);
		if(level!=null)
			cmd.addIntegerParam("level", level);

		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
}
