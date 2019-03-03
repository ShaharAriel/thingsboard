package com.devicewise.tr50.api.response.config;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.json.DwJsonResponseParser;

public class DwOpenConfigList extends DwOpenGenericResponse{
	
	private LinkedHashMap<String,LinkedHashMap<String,param>> params;
	
	public static class param{
		
		private String name;
		private boolean required;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isRequired() {
			return required;
		}
		public void setRequired(boolean required) {
			this.required = required;
		}
	}

	
	@Override
	public void parseCustomResponse(String json) throws JsonParseException, IOException, DwOpenException {
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(json);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, DwJsonResponseParser.isParserValidator());
		boolean paramsfound=false;
		
		jp.nextToken();
		
		if(jp.isClosed())
			throw new DwOpenException("JSON Response is Empty!");
	
		while(!jp.isClosed() && jp.getText().indexOf("get")==-1){
			jp.nextToken();
		}
		
		if(!jp.isClosed())
			paramsfound=true;
			
		if(!paramsfound)
			throw new DwOpenException("JSON Response has Invalid/Missing Correlation Id!");
		
		jp.nextToken();
		
		setAllFields(mapper.readValue(jp, this.getClass()));
		
	}


	public LinkedHashMap<String,LinkedHashMap<String,param>> getParams() {
		return params;
	}


	public void setParams(LinkedHashMap<String,LinkedHashMap<String,param>> params) {
		this.params = params;
	}

}
