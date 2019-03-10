package com.devicewise.tr50.interfaces;

import java.util.LinkedHashMap;

public interface IDwPacket {

	public abstract String getRequest() throws Exception;
	public abstract String getResponse();
	public abstract void setRequest(String request);
	public abstract void setResponse(String response);
	public abstract int addCommand(String corrId, IDwCommand command) throws Exception;
	public abstract LinkedHashMap<String,Object> getRawResponse();
	
}
