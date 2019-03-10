package com.devicewise.tr50.api.response;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.json.DwJsonResponseParser;

public abstract class DwOpenGenericResponse {

	protected boolean success = false;
	protected String[] errorMessages;
	protected int[] errorCodes;
	protected String jsonResponse;
	protected String corrId;
	protected static final ObjectMapper mapper = new ObjectMapper();

	public String getCorrId() {
		return corrId;
	}

	public void setCorrId(String corrId) {
		this.corrId = corrId;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String[] getErrormessages() {
		return errorMessages;
	}

	public void setErrormessages(String[] errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public void setErrorMessages(String[] errorMessages) {
		this.errorMessages = errorMessages;
	}

	public int[] getErrorcodes() {
		return errorCodes;
	}

	public void setErrorCodes(int[] errorCode) {
		this.errorCodes = errorCode;
	}
	
	public void setErrorcodes(int[] errorCode) {
		this.errorCodes = errorCode;
	}
	
	public void parseGenericResponse(String json) throws JsonParseException, JsonMappingException, IOException, DwOpenException{
		
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(json);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		jp.nextToken();
		
		while(!jp.getText().equalsIgnoreCase("success") && !jp.isClosed())
			jp.nextToken();
	
		if(jp.isClosed())
			throw new DwOpenException("JSON Response has no result");
		
		DwOpenResponse response = mapper.readValue(jp, DwOpenResponse.class);
		this.success = response.success;
		this.errorCodes = response.errorCodes;
		this.errorMessages = response.errorMessages;
		
		jp.close();
	}

	public void parseResponse(String json) throws JsonParseException, JsonMappingException, IOException, DwOpenException{
		setJsonResponse(json);
		
		if(corrId!=null){
			parseResponse(corrId,json);
		}

		else{

			parseGenericResponse(json);
			if(this.isSuccess())
				parseCustomResponse(json);
		}
	}

	@SuppressWarnings("unchecked")
	public void parseResponse(String corrId,String json) throws JsonParseException, JsonMappingException, IOException, DwOpenException{
		
		if(corrId==null)
			parseResponse(json);
		
		else{

			this.corrId = corrId;
			LinkedHashMap<String,String> jsons = new LinkedHashMap<String,String>();
			jsons = (LinkedHashMap<String,String>)mapper.readValue(new JsonFactory().createJsonParser(json),jsons.getClass());
			
			if(jsons.containsKey(corrId)){
				String jsonStr = mapper.writeValueAsString(jsons.get(corrId));
				parseGenericResponse(jsonStr);
				if(this.isSuccess())
					parseCustomResponse(jsonStr);
			}
		}
	}
	
	
	public void parseCustomResponse(String json) throws JsonParseException, IOException, DwOpenException {
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(json);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, DwJsonResponseParser.isParserValidator());
		boolean paramsfound=false;
		
		jp.nextToken();
		
		if(jp.isClosed())
			throw new DwOpenException("JSON Response is Empty!");
			
		while(!jp.isClosed() && !jp.getText().equalsIgnoreCase("params")){
			jp.nextToken();
		}
		
		if(!jp.isClosed())
			paramsfound=true;
			
		if(!paramsfound)
			return;
		
		jp.nextToken();
		
		setAllFields(mapper.readValue(jp, this.getClass()));
		
	}

	public void setAllFields(DwOpenGenericResponse response){
		for(Method method:response.getClass().getMethods()){
			try {
				if(method.getName().equalsIgnoreCase("isSuccess") || method.getName().equalsIgnoreCase("getErrormessages") || method.getName().equalsIgnoreCase("getErrorcodes") 
						|| method.getName().equalsIgnoreCase("getJsonResponse"))
					continue;
				if(method.getName().startsWith("get")){
					this.getClass().getMethod(method.getName().replaceFirst("get", "set"), method.getReturnType()).invoke(this, method.invoke(response, (Object[])null));
				}
				else if(method.getName().startsWith("is"))
				{
					this.getClass().getMethod(method.getName().replaceFirst("is", "set"), method.getReturnType()).invoke(this, method.invoke(response, (Object[])null));
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
			}

		}
	}

	public String getJsonResponse() {
		return jsonResponse;
	}

	public void setJsonResponse(String response) {
		this.jsonResponse = response;
	}
}
