package com.devicewise.tr50.api;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.FileHelper;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.interfaces.IDwOpenGeneric;
import com.devicewise.tr50.json.DwJsonObjectWithKey;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenGeneric implements IDwOpenGeneric{

	protected DwClient client;

	public DwOpenGeneric(DwClient client){
		this.client=client;
	}

	public void sendJSONrequest(String json, StringReply response) throws DwOpenException, IOException{
		DwPacket packet = new DwPacket();
		packet.setRequest(json);
		if(client.sendPacket(packet)==0)
			response.setValue(packet.getResponse());
	}

	public void executeJSONFile(String filename, StringReply response) throws IOException, DwOpenException{
		sendJSONrequest(FileHelper.toString(filename), response);
	}

	public void sendPacket(DwPacket packet) throws DwOpenException{
		client.sendPacket(packet);
	}

	public int sendParamsWithReply(String command, String corrId,Object response,Object...params) throws IOException, DwOpenException{
		int ret=0;

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(command);

		try{

			if(params!=null){
				for(int i=0;i<params.length;i+=2){
					if(params[i+1]==null)
						cmd.addStringParam((String)params[i], null);
					else if(params[i+1] instanceof Integer)
						cmd.addIntegerParam((String)params[i], ((Integer)params[i+1]).intValue());
					else if(params[i+1] instanceof Double)
						cmd.addDoubleParam((String)params[i], ((Double)params[i+1]).doubleValue());
					else if(params[i+1] instanceof Float)
						cmd.addFloatParam((String)params[i], ((Float)params[i+1]).floatValue());
					else if(params[i+1] instanceof String)
						cmd.addStringParam((String)params[i], ((String)params[i+1]));
					else if(params[i+1] instanceof Boolean)
						cmd.addBooleanParam((String)params[i], ((Boolean)params[i+1]).booleanValue());
					else if(params[i+1] instanceof DwJsonObjectWithKey[])
						cmd.addObjectArrayParamWithKey((String)params[i], ((DwJsonObjectWithKey[])params[i+1]));
					else if(params[i+1] instanceof int[])
						cmd.addIntegerArrayParam((String)params[i], ((int[])params[i+1]));
					else if(params[i+1] instanceof double[])
						cmd.addDoubleArrayParam((String)params[i], ((double[])params[i+1]));
					else if(params[i+1] instanceof float[])
						cmd.addFloatArrayParam((String)params[i], ((float[])params[i+1]));
					else if(params[i+1] instanceof boolean[])
						cmd.addBooleanArrayParam((String)params[i], ((boolean[])params[i+1]));
					else if(params[i+1] instanceof String[])
						cmd.addStringArrayParam((String)params[i], ((String[])params[i+1]));
					else if(params[i+1] instanceof Object[])
						cmd.addObjectArrayParam((String)params[i], ((Object[])params[i+1]));
					else
						cmd.addObjectParam((String)params[i], params[i+1]);
				}
				
				packet.addCommand(corrId,cmd);

			}	
		}catch(ArrayIndexOutOfBoundsException e){
			return(DwOpenErrors.API_INSUFFICIENT_PARAMS);
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
			else if(response instanceof DwOpenGenericResponse)
				((DwOpenGenericResponse)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		return ret;
	}

	public int sendParamsWithReplyAsync(String command, String corrId,DwOpenReceiveActionListener recv,Object response,Object...params) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenGenericResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);

		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(command);
		try{
			for(int i=0;i<params.length;i+=2){
				if(params[i+1]==null)
					cmd.addStringParam((String)params[i], null);
				else if(params[i+1] instanceof Integer)
					cmd.addIntegerParam((String)params[i], ((Integer)params[i+1]).intValue());
				else if(params[i+1] instanceof Double)
					cmd.addDoubleParam((String)params[i], ((Double)params[i+1]).doubleValue());
				else if(params[i+1] instanceof Float)
					cmd.addFloatParam((String)params[i], ((Float)params[i+1]).floatValue());
				else if(params[i+1] instanceof Boolean)
					cmd.addBooleanParam((String)params[i], ((Boolean)params[i+1]).booleanValue());
				else if(params[i+1] instanceof String)
					cmd.addStringParam((String)params[i], ((String)params[i+1]));
				else if(params[i+1] instanceof DwJsonObjectWithKey[])
					cmd.addObjectArrayParamWithKey((String)params[i], ((DwJsonObjectWithKey[])params[i+1]));
				else if(params[i+1] instanceof int[])
					cmd.addIntegerArrayParam((String)params[i], ((int[])params[i+1]));
				else if(params[i+1] instanceof double[])
					cmd.addDoubleArrayParam((String)params[i], ((double[])params[i+1]));
				else if(params[i+1] instanceof float[])
					cmd.addFloatArrayParam((String)params[i], ((float[])params[i+1]));
				else if(params[i+1] instanceof boolean[])
					cmd.addBooleanArrayParam((String)params[i], ((boolean[])params[i+1]));
				else if(params[i+1] instanceof String[])
					cmd.addStringArrayParam((String)params[i], ((String[])params[i+1]));
				else if(params[i+1] instanceof Object[])
					cmd.addObjectArrayParam((String)params[i], ((Object[])params[i+1]));
				else
					cmd.addObjectParam((String)params[i], params[i+1]);
				
				packet.addCommand(corrId,cmd);

			}
		}catch(ArrayIndexOutOfBoundsException e){
			return(DwOpenErrors.API_INSUFFICIENT_PARAMS);
		}catch(NullPointerException npe){
			return(DwOpenErrors.API_UNITIALIZED_PARAM_OBJECT);
		}catch(JsonGenerationException jge){
			return(DwOpenErrors.API_BAD_PARAM_OBJECT);
		}

		return(client.sendPacket(packet,recv,response));
	}
}
