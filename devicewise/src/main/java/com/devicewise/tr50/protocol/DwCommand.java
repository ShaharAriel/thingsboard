package com.devicewise.tr50.protocol;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.devicewise.tr50.interfaces.IDwCommand;
import com.devicewise.tr50.json.DwJsonObjectWithKey;

@JsonPropertyOrder({"command","params"})
public class DwCommand implements IDwCommand{

	private String command;
	private LinkedHashMap<String,Object> params;
	
	public DwCommand(String command){
		this.command = command;
		params = new LinkedHashMap<String,Object>();
	}
	
	public DwCommand(){
		params = new LinkedHashMap<String,Object>();
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void addParamsFromStringArrayNameValuePairs(String...params) throws IOException{
		for(int i=0;i<params.length;i+=2){
			addParam(params[i], params[i+1]);
		}
	}
	
	public void addParamsFromVectorNameValuePairs(Vector<String>params) throws IOException{
		for(int i=0;i<params.size();i+=2){
			addParam(params.elementAt(i), params.elementAt(i+1));
		}
	}
	
	public void addIntegerParam(String name,int value) throws IOException{
		addParam(name, value);
	}
	
	public void addIntegerArrayParam(String name,int[] value) throws IOException{
		addParam(name, value);
	}
	
	public void addFloatParam(String name,float value) throws IOException{
		addParam(name, value);
	}

	public void addFloatArrayParam(String name, float[] loc) throws IOException {
		addParam(name, loc);
	}
	
	public void addDoubleParam(String name,double value) throws IOException{
		addParam(name, value);
	}

	public void addDoubleArrayParam(String name, double[] loc) throws IOException {
		addParam(name, loc);
	}
	
	public void addBooleanParam(String name,boolean value) throws IOException{
		addParam(name, value);
	}
	
	public void addBooleanArrayParam(String name,boolean[] value) throws IOException{
		addParam(name, value);
	}
	
	public void addStringParam(String name,String value) throws IOException{
		addParam(name, value);
	}
	
	public void addStringArrayParam(String name, String[] tags) throws IOException {
		addParam(name, tags);
	}
	
	public void addObjectParam(String name,Object value) throws IOException{
		addParam(name,value);
	}
	

	public void addParamsFromObjectArray(Object[] params) throws IOException{
		for(int i=0;i<params.length;i+=2){
			
			if(params[i+1] instanceof String)
				addParam((String)params[i], (String)params[i+1]);
			
		}
	}
	
	public void addParamsFromObjectVector(Vector<Object> params){
		
	}

	public void endPacket() throws IOException {
	}

	public void addObjectArrayParam(String name,
			Object ... data) throws IOException {
		addParam(name, data);
	}
	
	
	public void addObjectArrayParamWithKey(String name,
			DwJsonObjectWithKey ... data) throws IOException {
		LinkedHashMap<String,DwJsonObjectWithKey> params = new LinkedHashMap<String,DwJsonObjectWithKey>();
		for(DwJsonObjectWithKey param:data)
			params.put(param.getKey(), param);
			addParam(name, params);
	}

	@Override
	public void setParams(LinkedHashMap<String, Object> params) {
		this.params = params;
		
	}

	@Override
	public LinkedHashMap<String, Object> getParams() {
		return params;
	}

	@Override
	public void addParam(String param, Object value) {
		params.put(param, value);
	}

	@Override
	public void deleteParam(String param) {
		params.remove(param);
	}
	
}
