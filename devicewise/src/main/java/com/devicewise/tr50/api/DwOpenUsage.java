package com.devicewise.tr50.api;

import java.io.IOException;

import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.usage.DwOpenUsageHistory;
import com.devicewise.tr50.api.response.usage.DwOpenUsageList;
import com.devicewise.tr50.constants.DwOpenCommands;
import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.listeners.DwOpenReceiveActionListener;
import com.devicewise.tr50.protocol.DwClient;
import com.devicewise.tr50.protocol.DwPacket;
import com.devicewise.tr50.protocol.DwCommand;

public class DwOpenUsage extends DwOpenGeneric{
	
	public DwOpenUsage(DwClient client){
		super(client);
	}

	public int apiHistory(String last, String series, Boolean showAll, Object response) throws IOException, DwOpenException{

		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_API_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("last",last,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenUsageHistory)
				((DwOpenUsageHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int apiHistory(String last, String series, Boolean showAll, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenUsageHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_API_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("last",last,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int apiHistory(String start, String end, String series, Boolean showAll, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_API_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenUsageHistory)
				((DwOpenUsageHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int apiHistory(String start, String end, String series, Boolean showAll, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
	
		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenUsageHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_API_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int apiList(Integer offset, Integer limit, Boolean showAll, String last, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_API_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		if(showAll!=null)
			cmd.addBooleanParam("showAll", showAll);
		cmd.addStringParam("last", last);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenUsageList)
				((DwOpenUsageList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int apiList(Integer offset, Integer limit, Boolean showAll, String last, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenUsageList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_API_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		if(showAll!=null)
			cmd.addBooleanParam("showAll", showAll);
		cmd.addStringParam("last", last);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int apiList(Integer offset, Integer limit, Boolean showAll, String start, String end, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_API_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		if(showAll!=null)
			cmd.addBooleanParam("showAll", showAll);
		cmd.addParamsFromStringArrayNameValuePairs("start", start,"end",end);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenUsageList)
				((DwOpenUsageList)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);		}
		
		return ret;
		
	}
	
	public int apiList(Integer offset, Integer limit, Boolean showAll, String start, String end, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{
		
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenUsageList))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_API_LIST);
		if(offset!=null)
			cmd.addIntegerParam("offset",offset);
		if(limit!=null)
			cmd.addIntegerParam("limit",limit);
		if(showAll!=null)
			cmd.addBooleanParam("showAll", showAll);
		cmd.addParamsFromStringArrayNameValuePairs("start", start,"end",end);
		packet.addCommand(DwOpenCommands.CORR_ID_LIST,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
	
	public int storageHistory(String last, String series, Boolean showAll, Object response) throws IOException, DwOpenException{

		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_STORAGE_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("last",last,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenUsageHistory)
				((DwOpenUsageHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int storageHistory(String last, String series, Boolean showAll, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenUsageHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_STORAGE_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("last",last,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int storageHistory(String start, String end, String series, Boolean showAll, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_STORAGE_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenUsageHistory)
				((DwOpenUsageHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int storageHistory(String start, String end, String series, Boolean showAll, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
	
		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenUsageHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_STORAGE_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}

	public int thingHistory(String last, String series, Boolean showAll, Object response) throws IOException, DwOpenException{

		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_THING_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("last",last,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenUsageHistory)
				((DwOpenUsageHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int thingHistory(String last, String series, Boolean showAll, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);

		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenUsageHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_THING_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("last",last,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
	}
	
	public int thingHistory(String start, String end, String series, Boolean showAll, Object response) throws IOException, DwOpenException{
		
		int ret=0;
		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_THING_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		ret=client.sendPacket(packet);

		if(ret==0){

			if(packet.getResponse()==null)
				return(DwOpenErrors.API_REPLY_NULL);
					
			if(response instanceof StringReply)
					((StringReply)response).setValue(packet.getResponse());
			else if(response instanceof DwOpenResponse)
				((DwOpenResponse)response).parseResponse(packet.getResponse());
			else if(response instanceof DwOpenUsageHistory)
				((DwOpenUsageHistory)response).parseResponse(packet.getResponse());
			else
				return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		}
		
		return ret;
		
	}
	
	public int thingHistory(String start, String end, String series, Boolean showAll, DwOpenReceiveActionListener recv, Object response) throws IOException, DwOpenException{

		if(response==null)
			return(DwOpenErrors.API_UNITIALIZED_REPLY_OBJECT);
	
		if(recv==null)
			return(DwOpenErrors.API_UNITIALIZED_ASYNC_RECEIVER_OBJECT);
		
		if(!(response instanceof StringReply || response instanceof DwOpenResponse || response instanceof DwOpenUsageHistory))
			return(DwOpenErrors.API_UNKNOWN_REPLY_OBJECT);
		
		DwPacket packet = new DwPacket(client);
		DwCommand cmd= new DwCommand();
		cmd.setCommand(DwOpenCommands.CMD_API_USAGE_THING_HISTORY);
		cmd.addParamsFromStringArrayNameValuePairs("start",start,"end",end,"series",series);
		if(showAll!=null)
		cmd.addBooleanParam("showAll", showAll);
		packet.addCommand(DwOpenCommands.CORR_ID_GET,cmd);
		
		return(client.sendPacket(packet,recv,response));
		
	}
}
