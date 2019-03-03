package com.devicewise.tr50.json;

import java.io.IOException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class DwJsonResponseParser {

	private static boolean parserValidator = false;

	public static boolean isParserValidator() {
		return parserValidator;
	}

	public static void setParserValidator(boolean parserValidator) {
		DwJsonResponseParser.parserValidator = parserValidator;
	}
	
	public static String getCorrelId(String jsonResponse) throws JsonParseException, IOException{
		
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(jsonResponse);
		while(jp.getCurrentToken()!=JsonToken.FIELD_NAME && !jp.isClosed()){
			jp.nextToken();
		}
		
		if(!jp.isClosed())
			return(jp.getText());
		
		return("");
	}
}
