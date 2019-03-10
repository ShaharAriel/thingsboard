package com.devicewise.tr50.api;

import java.io.IOException;
import java.util.Vector;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.file.DwOpenFileList;
import com.devicewise.tr50.api.response.file.DwOpenFileGet;
import com.devicewise.tr50.api.response.file.DwOpenFilePut;
import com.devicewise.tr50.api.response.file.DwOpenFileTagCloud;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenFile extends DwOpenGeneric{
	
	public DwOpenFile(DwClient client){
		super(client);
	}

	public int delete(String thingKey, String fileName, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_DELETE);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"fileName",fileName);
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
	
	public int delete(String thingKey, String fileName, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_DELETE);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"fileName",fileName);
		packet.addCommand(DwOpenCommands.CORR_ID_DO,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int get(String thingKey, String fileName, Object response,String...tags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_GET);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"fileName",fileName);
		if(tags!=null)
			cmd.addStringArrayParam("tags",tags);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenFileGet)
				((DwOpenFileGet)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int get(String thingKey, String fileName, DwOpenReceiveActionListener recv, Object response,String...tags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenFileGet))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_GET);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"fileName",fileName);
		if(tags!=null)
			cmd.addStringArrayParam("tags",tags);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int get(String thingKey, String fileName, String[] tags, Object response) throws IOException, DwOpenException{
		return(this.get(thingKey,  fileName, response, tags));
	}
	
	public int get(String thingKey, String fileName, Vector<String> tags, Object response) throws IOException, DwOpenException{
		return(this.get(thingKey,  fileName, response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int get(String thingKey, String fileName, String[] tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(this.get(thingKey,  fileName, recv,response, tags));
	}
	
	public int get(String thingKey, String fileName, Vector<String> tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(this.get(thingKey,  fileName, recv,response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int list(Object response,String...tags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_LIST);
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
			else if(response instanceof DwOpenFileList)
				((DwOpenFileList)response).parseResponse(packet.getResponse());			
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int list(DwOpenReceiveActionListener recv, Object response,String...tags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenFileList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_LIST);
		if(tags!=null)
			cmd.addStringArrayParam("tags",tags);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

	public int list(String[] tags, Object response) throws IOException, DwOpenException{
		return(this.list( response, tags));
	}
	
	public int list(Vector<String> tags, Object response) throws IOException, DwOpenException{
		return(this.list( response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int list(String[] tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(this.list(recv,response, tags));
	}
	
	public int list(Vector<String> tags, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(this.list(recv,response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int put(String thingKey, String fileName, Boolean Public,Object response,String...tags) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_PUT);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"fileName",fileName);
		if(tags!=null)
			cmd.addStringArrayParam("tags",tags);
		if(Public!=null)
			cmd.addBooleanParam("public", Public);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenFilePut)
				((DwOpenFilePut)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int put(String thingKey, String fileName, Boolean Public, DwOpenReceiveActionListener recv, Object response,String...tags) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenFilePut))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_PUT);
		cmd.addParamsFromStringArrayNameValuePairs("thingKey",thingKey,"fileName",fileName);
		if(tags!=null)
			cmd.addStringArrayParam("tags",tags);
		if(Public!=null)
			cmd.addBooleanParam("public", Public);
		packet.addCommand(DwOpenCommands.CORR_ID_PUT,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int put(String thingKey, String fileName, String[] tags, Boolean Public, Object response) throws IOException, DwOpenException{
		return(this.put(thingKey,  fileName, Public,response, tags));
	}
	
	public int put(String thingKey, String fileName, Vector<String> tags, Boolean Public,Object response) throws IOException, DwOpenException{
		return(this.put(thingKey,  fileName, Public, response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int put(String thingKey, String fileName, String[] tags, Boolean Public, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(this.put(thingKey,  fileName, Public, recv,response,tags));
	}
	
	public int put(String thingKey, String fileName, Vector<String> tags, Boolean Public, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		return(this.put(thingKey,  fileName, Public, recv,response, tags!=null?tags.toArray(new String[tags.size()]):null));
	}
	
	public int tagCloud(Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_TAG_CLOUD);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenFileTagCloud)
				((DwOpenFileTagCloud)response).parseResponse(packet.getResponse());			
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int tagCloud(DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);

		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenFileTagCloud))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_FILE_TAG_CLOUD);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}

}
