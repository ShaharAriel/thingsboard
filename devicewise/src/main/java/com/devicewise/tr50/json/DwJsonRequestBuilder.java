package com.devicewise.tr50.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.devicewise.tr50.constants.DwOpenCommands;

public class DwJsonRequestBuilder {

	private JsonFactory factory;
	private JsonGenerator g;
	private boolean initParams;
	private StringWriter sw;
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static ObjectMapper getMapper() {
		return mapper;
	}

	public DwJsonRequestBuilder() throws IOException{
		
		factory = new JsonFactory();
		sw = new StringWriter();
		g = factory.createJsonGenerator(sw);
		g.writeStartObject();
	}
	
	public DwJsonRequestBuilder(String sessionId) throws IOException {
		
		factory = new JsonFactory();
		sw = new StringWriter();
		g = factory.createJsonGenerator(sw);
		g.writeStartObject();
		g.writeObjectFieldStart(DwOpenCommands.CORR_ID_AUTH);
		g.writeStringField("sessionId", sessionId);
		g.writeEndObject();
		
	}
	
	public void startCommandBlock(String correlID) throws IOException{
		g.writeObjectFieldStart(correlID);
		setInitParams(false);
	}
	
	public void endCommand() throws JsonGenerationException, IOException{
		if(initParams)
			g.writeEndObject();
		g.writeEndObject();
	}
	
	public void addCommand(String correlID, String command) throws IOException{
		startCommandBlock(correlID);
		g.writeStringField("command", command);
		if(!isInitParams())
			initParams();
	}
	
	public void initParams() throws IOException {
		g.writeObjectFieldStart("params");
		setInitParams(true);
	}

	public void addStringParam(String paramName, String paramValue) throws IOException{
		g.writeStringField(paramName,paramValue);
	}
	
	public void addBooleanParam(String paramName, boolean paramValue) throws IOException{
		g.writeBooleanField(paramName,paramValue);
	}
	
	public void addIntParam(String paramName, int paramValue) throws IOException{
			g.writeNumberField(paramName,paramValue);
	}
	
	public void addLongParam(String paramName, long paramValue) throws IOException{
		g.writeNumberField(paramName,paramValue);
	}
	
	public void addFloatParam(String paramName, float paramValue) throws IOException{
		g.writeNumberField(paramName,paramValue);
	}
	
	public void addDoubleParam(String paramName, double paramValue) throws IOException{
		g.writeNumberField(paramName,paramValue);
	}
	
	public void addObjectParam(String paramName, Object obj) throws IOException{
		mapper.reader(obj.getClass());
		g.setCodec(mapper);
		g.writeObjectField(paramName, obj);
	}
	
	public void addStringArrayParam(String paramName, String[] paramValues) throws IOException{
		g.writeArrayFieldStart(paramName);
		for(String param:paramValues){
			g.writeString(param);
		}
		g.writeEndArray();

	}
	
	public void addStringVectorParam(String paramName, Vector<?> paramValues) throws IOException{
		g.writeArrayFieldStart(paramName);
		for(Object param:paramValues){
			g.writeString((String)param);
		}
		g.writeEndArray();

	}
	
	public void addIntArrayParam(String paramName, int[] paramValues) throws IOException{
		g.writeArrayFieldStart(paramName);
		for(int param:paramValues){
			g.writeNumber(param);
		}
		g.writeEndArray();

	}
	
	public void addFloatArrayParam(String paramName, float[] paramValues) throws IOException{
		g.writeArrayFieldStart(paramName);
		for(float param:paramValues){
			g.writeNumber(param);
		}
		g.writeEndArray();

	}

	public void addDoubleArrayParam(String paramName, double[] paramValues) throws IOException{
		g.writeArrayFieldStart(paramName);
		for(double param:paramValues){
			g.writeNumber(param);
		}
		g.writeEndArray();

	}
	
	public void addBooleanArrayParam(String paramName, boolean[] paramValues) throws IOException {
		g.writeArrayFieldStart(paramName);
		for(boolean param:paramValues){
			g.writeBoolean(param);
		}
		g.writeEndArray();
	}
	
	public void addObjectArrayParam(String paramName, Object ... paramValues) throws IOException{
		mapper.reader(paramValues[0].getClass());
		g.setCodec(mapper);
		g.writeArrayFieldStart(paramName);
		for(Object param:paramValues){
			g.writeObject(param);
		}
		g.writeEndArray();
	}
	
	public boolean isInitParams() {
		return initParams;
	}

	public void setInitParams(boolean initParams) {
		this.initParams = initParams;
	}

	public void closeJSON() throws IOException {
		g.writeEndObject();
		g.close();
	}

	public String ToString() {
		return sw.toString();
	}
	
	public void close() throws IOException{
		sw.close();
	}

	public void addObjectArrayParamWithKey(String name, Object...data) throws JsonGenerationException, IOException {
		mapper.reader(data[0].getClass());
		g.setCodec(mapper);
		g.writeObjectFieldStart(name);
		for(Object dataValue: data)
			g.writeObjectField(((DwJsonObjectWithKey)dataValue).getKey(),dataValue);
		g.writeEndObject();
	}
	
	public void addObjectParamWithKey(String name, Object data) throws JsonGenerationException, IOException {
		mapper.reader(data.getClass());
		g.setCodec(mapper);
		g.writeObjectFieldStart(name);
		g.writeObjectField(((DwJsonObjectWithKey)data).getKey(),data);
	}

}
