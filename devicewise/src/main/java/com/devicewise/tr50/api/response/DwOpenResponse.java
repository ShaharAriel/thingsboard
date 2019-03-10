package com.devicewise.tr50.api.response;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.devicewise.tr50.exception.DwOpenException;

public class DwOpenResponse extends DwOpenGenericResponse {
	
	public void parseResponse(String json) throws JsonParseException, JsonMappingException, IOException, DwOpenException{
		setJsonResponse(json);
		parseGenericResponse(json);
	}

}
