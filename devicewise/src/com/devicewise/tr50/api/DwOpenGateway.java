package com.devicewise.tr50.api;

import java.io.IOException;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenGateway extends DwOpenGeneric{
	
	public DwOpenGateway(DwClient client){
		super(client);
	}

	public int register(String dwProduct, String dwPlatform, String dwVersion, String appVersion, String remShell, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GATEWAY_REGISTER);
		cmd.addParamsFromStringArrayNameValuePairs("dwProduct",dwProduct,"dwPlatform",dwPlatform,"dwVersion",dwVersion,"appVersion",appVersion,"remShell",remShell);
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
	
	public int register(String make, String model, String fwVersion, String remAt, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_GATEWAY_REGISTER);
		cmd.addParamsFromStringArrayNameValuePairs("make",make,"model",model,"fwVersion",fwVersion,"remAt",remAt);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

}
