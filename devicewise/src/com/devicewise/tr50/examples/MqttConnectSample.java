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

import org.eclipse.paho.client.mqttv3.MqttException;

import com.devicewise.tr50.api.response.session.DwOpenSessionInfo;
import com.devicewise.tr50.clients.DwMqttClient;
import com.devicewise.tr50.exception.DwOpenException;

public class MqttConnectSample {
	
	private static String openUrl;
	private static String sessionId;
	private static String user;
	private static String password;
	private static String thingKey;
	private static String appToken;
	
	public static void main(String[] args){
		
		try{
			if(args.length==0){
				throw new Exception("Insuffucient Args! ");
			}
			else
				parseArgs(args);
		}catch(Exception e){
			e.printStackTrace(System.out);
			printHelpInfo();
			System.exit(-1);
		}
		
		try {
			
			int ret=0;
			
			ret = mqtt_connect_as_user();
			if(ret!=0)
				System.out.println("MQTT connect as User failed with error code"+ret);
			
			ret = mqtt_connect_as_thing();
			if(ret!=0)
				System.out.println("MQTT connect as Thing failed with error code"+ret);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DwOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static int mqtt_connect_as_user() throws IOException, MqttException, DwOpenException{
		
		if(user==null || password==null){
			System.out.println("No User And/or Password Specified. Skipping mqtt_connect_as_user()");
			return(-1);
		}
		
		int ret=0;
		DwMqttClient client = new DwMqttClient();
		
		client.initialize(openUrl, true, "tr50_sample_app");
		if(ret==0)
			ret=client.authenticate(user, password);
		
		DwOpenSessionInfo session = new DwOpenSessionInfo();
		client.getWorker().Session().info(session);
		
		if(session.isSuccess()){
			
			System.out.println("User "+session.getUserName()+" Connected!");
			
			System.out.println("Session Protocol: "+session.getConnInfo().getProtocol());
			System.out.println("Remote Addr     : "+session.getConnInfo().getRemoteAddr());
			System.out.println("Org Key         : "+session.getOrgKey());
		}
		else{
			System.out.println("Failed to get session info with Error: "+session.getErrorcodes()[0]+" ["+session.getErrormessages()[0]+"]");
		}
		
		System.out.println();
		client.disconnect();
		return ret;
	}
	
	public static int mqtt_connect_as_thing() throws IOException, DwOpenException, MqttException{
		
		if(thingKey==null || appToken==null){
			System.out.println("No ThingKey And/or AppToken Specified. Skipping mqtt_connect_as_thing()");
			return(-1);
		}
		
		int ret=0;
		DwMqttClient client = new DwMqttClient();
		client.initialize(openUrl, true, "tr50_sample_app");
		if(ret==0)
			ret=client.authenticate(thingKey,appToken);
		
		DwOpenSessionInfo session = new DwOpenSessionInfo();
		client.getWorker().Session().info(session);
		
		if(session.isSuccess()){
			
			System.out.println("Thing "+session.getThingKey()+" Connected!");
			
			System.out.println("Session Protocol: "+session.getConnInfo().getProtocol());
			System.out.println("Remote Addr     : "+session.getConnInfo().getRemoteAddr());
			System.out.println("Org Key         : "+session.getOrgKey());
		}
		else{
			System.out.println("Failed to get session info with Error: "+session.getErrorcodes()[0]+" ["+session.getErrormessages()[0]+"]");
		}
		
		System.out.println();
		client.disconnect();
		return ret;
		
	}
	
	public static void parseArgs(String[] args) throws Exception{
		
		for(int i=0;i<args.length;i++){
			
			 if ("-o".equals(args[i])) 
			 {
				 setOpenUrl(args[++i]);
			 }
			 else if ("-u".equals(args[i])) 
			 {
				 setUser(args[++i]);
			 }
			 else if ("-p".equals(args[i])) 
			 {
				 setPassword(args[++i]);
			 }
			 else if ("-t".equals(args[i])) 
			 {
				 setThingKey(args[++i]);
			 }
			 else if ("-a".equals(args[i])) 
			 {
				 setAppToken(args[++i]);
			 }
			 else throw new Exception("Unknown argument: "+args[i]);
		}
	}
	
	public static void printHelpInfo(){
		
		System.out.println("Usage: -o openserverurl [-a appToken] [-t thingKey] [-u username] [-p password] ");
		
		System.out.println("\t-o\tOpen Server URL");
		System.out.println("\t-t\tThing Key to Connect to Open Server (As Thing)");
		System.out.println("\t-a\tApplication Token to Connect to Open Server (As Thing)");
		System.out.println("\t-u\tUsername to Connect to Open Server (As User)");
		System.out.println("\t-p\tPassword to Connect To Open Server (As User)");
		System.out.println("");
		
	}

	public static String getOpenUrl() {
		return openUrl;
	}

	public static void setOpenUrl(String _openUrl) {
		openUrl = _openUrl;
	}

	public String getSessionId() {
		return sessionId;
	}

	public static void setSessionId(String _sessionId) {
		sessionId = _sessionId;
	}

	public String getUser() {
		return user;
	}

	public static void setUser(String _user) {
		user = _user;
	}

	public String getPassword() {
		return password;
	}

	public static void setPassword(String _password) {
		password = _password;
	}

	public String getThingKey() {
		return thingKey;
	}

	public static void setThingKey(String _thingKey) {
		thingKey = _thingKey;
	}

	public String getAppToken() {
		return appToken;
	}

	public static void setAppToken(String _appToken) {
		appToken = _appToken;
	}

}
