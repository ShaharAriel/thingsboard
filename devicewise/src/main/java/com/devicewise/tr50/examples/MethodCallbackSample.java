/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 ILS Technology, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.devicewise.tr50.examples;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.devicewise.tr50.api.params.DwOpenThingDefMethod;
import com.devicewise.tr50.api.params.DwOpenThingDefMethod.CompletionVariable;
import com.devicewise.tr50.api.params.DwOpenThingDefMethod.NotificationVariable;
import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.method.DwOpenMethodExec;
import com.devicewise.tr50.clients.DwMqttClient;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.examples.methods.MethodDumpParams;
import com.devicewise.tr50.examples.methods.MethodReturnError;
import com.devicewise.tr50.examples.methods.MethodReturnOutParams;

public class MethodCallbackSample{
	
	private DwMqttClient mqttClient;
	
	public DwMqttClient getMqttClient() {
		return mqttClient;
	}

	public void setMqttClient(DwMqttClient mqttClient) {
		this.mqttClient = mqttClient;
	}

	private String openUrl;
	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	private String thingKey;
	public String getThingKey() {
		return thingKey;
	}

	public void setThingKey(String thingKey) {
		this.thingKey = thingKey;
	}

	private String appToken;
	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public static void main(String[] args){

		MethodCallbackSample sampleApp = new MethodCallbackSample();

		try{
			if(args.length==0){
				throw new Exception("Insuffucient Args! ");
			}
			else
				parseArgs(sampleApp,args);
		}catch(Exception e){
			e.printStackTrace();
			printHelpInfo();
			System.exit(-1);
		}

		try {

			sampleApp.tr50_connect_mqtt();
			
			sampleApp.tr50_method_init();

			sampleApp.tr50_test_method_dump_params();
			
			sampleApp.tr50_test_method_return_error();

			sampleApp.tr50_test_method_output_params();
			
			sampleApp.getMqttClient().disconnect();
			

		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void tr50_method_init(){
		
		DwOpenThingDefMethod method = new DwOpenThingDefMethod();
		
		NotificationVariable nvar1 = new NotificationVariable();
		NotificationVariable nvar2 = new NotificationVariable();
		NotificationVariable nvar3 = new NotificationVariable();
		CompletionVariable cvar1 = new CompletionVariable();
		CompletionVariable cvar2 = new CompletionVariable();
		CompletionVariable cvar3 = new CompletionVariable();
		
		LinkedHashMap<String,NotificationVariable> nvars = new LinkedHashMap<String,NotificationVariable>();
		LinkedHashMap<String,CompletionVariable> cvars = new LinkedHashMap<String,CompletionVariable>();
				
		method.setKey("remote_method_sample");
		method.setName("Sample Remote Method");

		nvar1.setName("Input Integer Array");
		nvar1.setType("int");
		nvar1.setCount(25);
		
		nvar2.setName("Input Integer Scalar");
		nvar2.setType("int");
		nvar2.setCount(1);
		
		nvar3.setName("Input String");
		nvar3.setType("string");
		nvar3.setCount(1);
		
		nvars.put("input_integer_array", nvar1);
		nvars.put("input_integer", nvar2);
		nvars.put("input_string", nvar3);
		
		cvar1.setName("Output Bool Scalar");
		cvar1.setType("bool");
		cvar1.setCount(1);
		
		cvar2.setName("Output Float Array");
		cvar2.setType("float");
		cvar2.setCount(25);
		
		cvar3.setName("Output Integer Scalar");
		cvar3.setType("int");
		cvar3.setCount(1);
		
		cvars.put("output_bool", cvar1);
		cvars.put("output_float_array", cvar2);
		cvars.put("output_integer", cvar3);
		
		method.setCompletionVariables(cvars);
		method.setNotificationVariables(nvars);
		
		try {
			DwOpenResponse response = new DwOpenResponse();
			mqttClient.getWorker().ThingDef().update("default", null, null, null, null, null, null, new DwOpenThingDefMethod[]{method}, null, response, (String[])null);
			if(!response.isSuccess())
				System.out.println("Method Definition Creation Failed with Error ("+response.getErrorcodes()[0]+") : "+response.getErrormessages()[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DwOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void tr50_test_method_output_params() {
		
		MethodReturnOutParams outputMethod   = new MethodReturnOutParams("remote_method_sample");
		LinkedHashMap<String,Object> params = new LinkedHashMap<String,Object>();
		DwOpenMethodExec response = new DwOpenMethodExec();
		
		params.put("input_integer_array", new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25});
		params.put("input_string", "-13");
		params.put("input_integer", 2);
		
		int ret=0;
		if((ret=mqttClient.registerMethod(outputMethod))==0)
			System.out.println("Method Return Out Params Registered Successfully");
		else{
				System.out.println("Method Return Out Params Registration failed with error code: "+ret);
				return;
		}
		try {
			
			mqttClient.getWorker().Method().exec(this.getThingKey(), "remote_method_sample", false, 10, params, response);
			if(response.isSuccess()){
				System.out.println("Method Return Out Params Executed Successfully!");
				System.out.println("Output Params");
				for(String outParam:response.getParams().keySet())
					System.out.println("Param= "+outParam+" Value= "+response.getParams().get(outParam));
			}
			else
				System.out.println("Method Return Out Params Execution Failed with Error: "+response.getErrormessages()[0]+" ("+response.getErrorcodes()[0]+")");
				
			
		} catch (IOException | DwOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((ret=mqttClient.unregisterMethod(outputMethod))!=0){
			System.out.println("Unregister Method Return Out Failed with error code: "+ret);
		}
		
	}

	private void tr50_test_method_return_error() {
		
		MethodReturnError errMethod   = new MethodReturnError("remote_method_sample");
		LinkedHashMap<String,Object> params = new LinkedHashMap<String,Object>();
		DwOpenMethodExec response = new DwOpenMethodExec();
		
		params.put("input_integer_array", new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25});
		params.put("input_string", "-13");
		params.put("input_integer", 2);
		
		int ret=0;
		if((ret=mqttClient.registerMethod(errMethod))==0)
			System.out.println("Method Return Error Registered Successfully");
		else{
				System.out.println("Method Return Error Registration failed with error code: "+ret);
				return;
		}
		try {
			
			mqttClient.getWorker().Method().exec(this.getThingKey(), "remote_method_sample", false, 10, params, response);
			if(response.isSuccess())
				System.out.println("Method Return Error Executed Successfully!");
			else
				System.out.println("Method Return Error Execution Failed with Error: "+response.getErrormessages()[0]+" ("+response.getErrorcodes()[0]+")");
				
			
		} catch (IOException | DwOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((ret=mqttClient.unregisterMethod(errMethod))!=0){
			System.out.println("Unregister Method Return Error Failed with error code: "+ret);
		}
		
	}

	private void tr50_test_method_dump_params() {
		
		MethodDumpParams dumpParams   = new MethodDumpParams("remote_method_sample");
		LinkedHashMap<String,Object> params = new LinkedHashMap<String,Object>();
		DwOpenMethodExec response = new DwOpenMethodExec();
		
		params.put("input_integer_array", new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25});
		params.put("input_string", "-13");
		params.put("input_integer", 2);
		
		int ret=0;
		if((ret=mqttClient.registerMethod(dumpParams))==0)
			System.out.println("Method Dump Params Registered Successfully");
		else{
				System.out.println("Method Dump Params Registration failed with error code: "+ret);
				return;
		}
		
		try {
			
			mqttClient.getWorker().Method().exec(this.getThingKey(), "remote_method_sample", false, 10, params, response);
			if(response.isSuccess())
				System.out.println("Dump Params Executed Successfully!");
			else
				System.out.println("Dump Params Execution Failed with Error: "+response.getErrormessages()[0]+" ("+response.getErrorcodes()[0]+")");
				
			
		} catch (IOException | DwOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((ret=mqttClient.unregisterMethod(dumpParams))!=0){
			System.out.println("Unregister Dump Params Failed with error code: "+ret);
		}
		
	}

	public void tr50_connect_mqtt() throws MqttException, IOException {

		mqttClient = new DwMqttClient();
		mqttClient.initialize(openUrl, true, "tr50_sysInfo_client");
		mqttClient.authenticate(thingKey, appToken);

	}
	
	public static void parseArgs(MethodCallbackSample sampleApp, String[] args) throws Exception {

		for(int i=0;i<args.length;i++){

			if ("-o".equals(args[i])) 
			{
				sampleApp.setOpenUrl(args[++i]);
			}
			else if ("-t".equals(args[i])) 
			{
				sampleApp.setThingKey(args[++i]);
			}
			else if ("-a".equals(args[i])) 
			{
				sampleApp.setAppToken(args[++i]);
			}
			else throw new Exception("Unknown argument: "+args[i]);
		}

	}

	public static void printHelpInfo() {

		System.out.println("Usage: -o openserverurl -a appToken -t thingKey ");

		System.out.println("\t-o\tOpen Server URL");
		System.out.println("\t-t\tThing Key to Publish Sys Info Data ");
		System.out.println("\t-a\tApplication Token to Authenticate to an Open Server Organization");
		System.out.println("");

	}

}
