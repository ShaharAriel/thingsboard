package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.method.DwOpenMethodExec;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenMethod extends DwOpenGeneric{
	
	public DwOpenMethod(DwClient client){
		super(client);
	}

	public int exec(String thingKey, String method, Boolean singleton, Integer ackTimeout, LinkedHashMap<String,Object> params, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_METHOD_EXEC);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"method",method);
		if(ackTimeout!=null)
			cmd.addIntegerParam("ackTimeout", ackTimeout);
		if(singleton!=null)
			cmd.addBooleanParam("singleton",singleton);
		if(params!=null && params.size()!=0)
			cmd.addObjectParam("params", params);
		packet.addCommand(DwOpenCommands.CORR_ID_EXEC,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenMethodExec)
				((DwOpenMethodExec)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int exec(String thingKey, String method, Boolean singleton, Integer ackTimeout, LinkedHashMap<String,Object> params, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenMethodExec))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_METHOD_EXEC);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"method",method);
		if(ackTimeout!=null)
			cmd.addIntegerParam("ackTimeout", ackTimeout);
		if(singleton!=null)
			cmd.addBooleanParam("singleton",singleton);
		if(params!=null && params.size()!=0)
			cmd.addObjectParam("params", params);
		packet.addCommand(DwOpenCommands.CORR_ID_EXEC,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
}
