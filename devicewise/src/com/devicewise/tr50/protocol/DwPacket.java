package com.devicewise.tr50.protocol;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.interfaces.IDwCommand;
import com.devicewise.tr50.interfaces.IDwPacket;
import com.devicewise.tr50.json.DwJsonRequestBuilder;

public class DwPacket implements IDwPacket{

	private Vector<String> correlIDs;
	private String request;
	private String response;
	private DwJsonRequestBuilder jsonBuilder;
	private boolean closed=false;
	
	public DwPacket() throws IOException{
		setJsonBuilder(new DwJsonRequestBuilder());
		correlIDs = new Vector<String>();
	}
	
	public DwPacket(DwClient client) throws IOException{
		if(client.getSessionId()!=null)
			setJsonBuilder(new DwJsonRequestBuilder(client.getSessionId()));
		else
			setJsonBuilder(new DwJsonRequestBuilder());
		correlIDs = new Vector<String>();
	}
	
	public Vector<String> getCorrelIDs() {
		return correlIDs;
	}
	
	public String getRequest() {
		if(closed)
			return request;
		else{
			try {
				this.endPacket();
				return request;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	public void setRequest(String jsonRequest) {
		this.request = jsonRequest;
		closed = true;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String jsonResponse) {
		this.response = jsonResponse;
	}

	public DwJsonRequestBuilder getJsonBuilder() {
		return jsonBuilder;
	}

	public void setJsonBuilder(DwJsonRequestBuilder jsonBuilder) {
		this.jsonBuilder = jsonBuilder;
	}

	public void endPacket() throws IOException {
		
		jsonBuilder.closeJSON();
		setRequest(jsonBuilder.ToString());
		jsonBuilder.close();
		closed = true;
	}

	@Override
	public int addCommand(String corrId, IDwCommand command) throws IOException {
		
		if(command.getCommand()==null || command.getCommand().equals(""))
			return(DwOpenErrors.API_COMMAND_KEY_NOT_INITIALIZED);
		if(corrId==null || corrId.equals(""))
			return(DwOpenErrors.API_COMMAND_CORR_ID_NOT_SET);
		if(correlIDs.contains(corrId))
			return(DwOpenErrors.API_COMMAND_CORR_ID_EXISTS);
		if(closed)
			return(DwOpenErrors.API_COMMAND_ADD_FAILED_PACKET_CLOSED);
			
		correlIDs.add(corrId);
		jsonBuilder.addObjectParam(corrId, command);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LinkedHashMap<String,Object> getRawResponse() {

		LinkedHashMap<String,Object> rawParams = null;
		// TODO Auto-generated method stub
		if(!closed){
			try {
				 endPacket();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			
			rawParams = DwJsonRequestBuilder.getMapper().readValue(new JsonFactory().createJsonParser(getResponse()),(new LinkedHashMap<String,Object>()).getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rawParams;		
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String,Object> getRawResponse(String corrId) {

		LinkedHashMap<String,Object> rawParams = null;
		// TODO Auto-generated method stub
		if(!closed){
			try {
				 endPacket();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			
			rawParams = DwJsonRequestBuilder.getMapper().readValue(new JsonFactory().createJsonParser(getResponse()),(new LinkedHashMap<String,Object>()).getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (LinkedHashMap<String,Object>)rawParams.get(corrId);		
	}

}
