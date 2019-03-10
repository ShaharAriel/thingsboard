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

import java.io.File;
import java.io.IOException;
//import java.lang.management.ManagementFactory;
//import com.sun.management.OperatingSystemMXBean;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.devicewise.tr50.api.params.DwOpenAlarmParam;
import com.devicewise.tr50.api.params.DwOpenAlarmParam.AlarmState;
import com.devicewise.tr50.api.response.DwOpenResponse;
import com.devicewise.tr50.api.response.thing.DwOpenThingFind;
import com.devicewise.tr50.clients.DwMqttClient;
import com.devicewise.tr50.exception.DwOpenException;

public class SysInfoSample {

	private String openUrl;
	private String thingKey;
	private String appToken;
	private int pollRate = 30;
	private DwMqttClient mqttClient;

	public static void main(String[] args) {

		SysInfoSample sysInfo = new SysInfoSample();

		try{
			if(args.length==0){
				throw new Exception("Insuffucient Args! ");
			}
			else
				parseArgs(sysInfo,args);
		}catch(Exception e){
			e.printStackTrace();
			printHelpInfo();
			System.exit(-1);
		}

		try {

			sysInfo.tr50_connect_mqtt();

			sysInfo.tr50_initialize_properties_alarms();

//			sysInfo.tr50_publish_attribute_num_procs();

			while(true){

				System.out.println("\nCalling CPU TR50 Property Publish");
//				sysInfo.tr50_publish_property_cpu();
				System.out.println("\nCalling CPU TR50 Alarm Publish");
//				sysInfo.tr50_publish_alarm_cpu();

				System.out.println("\nCalling Disk TR50 Property Publish");
				sysInfo.tr50_publish_property_disk();
				System.out.println("\nCalling Disk TR50 Alarm Publish");
				sysInfo.tr50_publish_alarm_disk();

				Thread.sleep((long)(1000*sysInfo.pollRate));
			}

		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DwOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/*
	public void tr50_publish_attribute_num_procs() throws IOException, DwOpenException {

		DwOpenResponse resp = new DwOpenResponse();
		
		mqttClient.getWorker().Thing().attributeSet(thingIdOrKey, isThingKey, attrKey, attrValue, response)
		System.out.println("Setting attribute num_procs to value "+Runtime.getRuntime().availableProcessors());
		mqttClient.getWorker().Thing().attributeSet(thingKey, "num_procs", Integer.toString(Runtime.getRuntime().availableProcessors()), resp);
		
		if(!resp.isSuccess()){
			System.out.println("Failed to publish Attribute num_procs for thing with key "+thingKey+" with error "+resp.getErrormessages()[0]+" ("+resp.getErrorcodes()+")");
		}

	}
*/

	public void tr50_initialize_properties_alarms() throws IOException, DwOpenException {

		File[] roots = File.listRoots();
		DwOpenResponse resp = new DwOpenResponse();

		DwOpenThingFind thingFind = new DwOpenThingFind();
		mqttClient.getWorker().Thing().find(thingKey, thingFind);

		if(thingFind.isSuccess())
		{
			ArrayList<DwOpenAlarmParam> alarms = new ArrayList<>();

			DwOpenAlarmParam alarm_cpu_usage = new DwOpenAlarmParam();
			alarm_cpu_usage.setKey("alarm_cpu");
			alarm_cpu_usage.setName("CPU Alarm");

			AlarmState state_low = new AlarmState();
			state_low.setName("LOW");
			state_low.setColor("Yellow");

			AlarmState state_high = new AlarmState();
			state_high.setName("HIGH");
			state_high.setColor("Red");

			AlarmState state_med = new AlarmState();
			state_med.setName("NORMAL");
			state_med.setColor("Green");

			ArrayList<AlarmState> states = new ArrayList<AlarmState>();
			states.add(state_low);
			states.add(state_med);
			states.add(state_high);
			alarm_cpu_usage.setStates(states);

			alarms.add(alarm_cpu_usage);

			for(File root : roots){

				DwOpenAlarmParam alarm_disk_usage = new DwOpenAlarmParam();
				alarm_disk_usage.setKey("alarm_disk_"+getName(root));
				alarm_disk_usage.setName("Alarm For Disk "+getName(root));
				alarm_disk_usage.setStates(states);

				alarms.add(alarm_disk_usage);

			}

			//mqttClient.getWorker().ThingDef().update(idOrKey, isKey, name, autoDefProps, autoDefAttrs, attributes, alarms, props, methods, tunnels, response, unset);
			mqttClient.getWorker().ThingDef().update(thingFind.getDefKey(), null, true, true, null,
					alarms.toArray(new DwOpenAlarmParam[alarms.size()]), null, null, null, resp, (String[])null);

			if(!resp.isSuccess())
			{
				System.out.println("ThingDef Update Failed with error "+resp.getErrormessages()[0]+" ("+resp.getErrorcodes()+")");
			}

		}

		else
			System.out.println("Thing with specfied thingKey "+thingKey+" does not exist!");

	}


	private String getName(File root) {
		// TODO Auto-generated method stub
		if(root.getPath().indexOf(":")!=-1)
			return(root.getPath().substring(0, root.getPath().indexOf(":")));
		else if(root.getPath().equals("/"))
			return("linux_root");
		else
			return(root.getPath().substring(1,root.getPath().length()).replaceAll("/", "_"));

	}

	public void tr50_publish_alarm_disk() throws IOException, DwOpenException {

		File[] roots = File.listRoots();
		DwOpenResponse resp = new DwOpenResponse();

		for(File root : roots){

			//mqttClient.getWorker().Alarm().publish(thingIdOrKey, isThingKey, timestamp, corrId, key, state, message, response);

			if (0.9<(double)((double)root.getFreeSpace()/(double)root.getTotalSpace())){
				System.out.println("Alarm Publish for alarm_disk_"+getName(root)+" State = 0");
				mqttClient.getWorker().Alarm().publish(thingKey, null, null, "alarm_disk_"+getName(root), 0, "Disk Usage Is Low", resp);
			}

			else if (0.10>(double)((double)root.getFreeSpace()/(double)root.getTotalSpace())){
				System.out.println("Alarm Publish for alarm_disk_"+getName(root)+" State = 2");
				mqttClient.getWorker().Alarm().publish(thingKey, null, null, "alarm_disk_"+getName(root), 2, "Disk Usage Is High", resp);
					
			}

			else{
				System.out.println("Alarm Publish for alarm_disk_"+getName(root)+" State = 1");
				mqttClient.getWorker().Alarm().publish(thingKey, null, null, "alarm_disk_"+getName(root), 1, "Disk Usage Is Normal", resp);
			}

			if(!resp.isSuccess())
				System.out.println("Failed to publish Alarm for key alarm_disk_"+getName(root)+" with error "+resp.getErrormessages()[0]+" ("+resp.getErrorcodes()+")");

		}

	}

	public void tr50_publish_property_disk() throws IOException, DwOpenException {

		File[] roots = File.listRoots();
		DwOpenResponse resp = new DwOpenResponse();

		for(File root : roots){
			
			String propKey = "free_disk_"+getName(root);
			double propValue = root.getFreeSpace();

			//mqttClient.getWorker().Property().publish(thingIdOrKey, isThingKey, timestamp, propKey, propValue, corrId, response);
			
			System.out.println("Property publish for property "+propKey+" value = "+propValue);
			mqttClient.getWorker().Property().publish(thingKey, null, propKey, propValue, null, resp);
			if(!resp.isSuccess())
				System.out.println("Failed to publish Data for property free_disk_"+getName(root)+"with error "+resp.getErrormessages()[0]+" ("+resp.getErrorcodes()+")");

			propKey = "used_disk_"+getName(root);
			propValue = root.getTotalSpace()-root.getFreeSpace();
			
			System.out.println("Property publish for property "+propKey+" value = "+propValue);
			mqttClient.getWorker().Property().publish(thingKey, null, propKey, propValue, null, resp);
			if(!resp.isSuccess())
				System.out.println("Failed to publish Data for property used_disk_"+getName(root)+"with error "+resp.getErrormessages()[0]+" ("+resp.getErrorcodes()+")");

		}

	}


/*
	public void tr50_publish_alarm_cpu() throws IOException, DwOpenException {

		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		DwOpenResponse resp = new DwOpenResponse();

		if(osBean.getSystemCpuLoad()>0.75){
			System.out.println("Alarm Publish for alarm_cpu State = 2");
			mqttClient.getWorker().Alarm().publish(thingKey, null, null, "alarm_cpu", 2, "CPU Usage is HIGH", resp);
		}
		else if (osBean.getSystemCpuLoad()<0.20){
			System.out.println("Alarm Publish for alarm_cpu State = 0");
			mqttClient.getWorker().Alarm().publish(thingKey, null, null, "alarm_cpu", 0, "CPU Usage is LOW", resp);

		}
		else{
			System.out.println("Alarm Publish for alarm_cpu State = 1");
			mqttClient.getWorker().Alarm().publish(thingKey, null, null, "alarm_cpu", 1, "CPU Usage is MEDIUM", resp);
		}

		if(!resp.isSuccess()){
			System.out.println("Failed to publish Alarm Data for alarm_cpu_usage for thing with key "+thingKey+"with error "+resp.getErrormessages()[0]+" ("+resp.getErrorcodes()+")");
		}


	}
*/

/*
	public void tr50_publish_property_cpu() throws IOException, DwOpenException, InterruptedException {

		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		DwOpenResponse resp = new DwOpenResponse();
		
		String propKey = "cpu_usage";
		double propValue = osBean.getSystemCpuLoad()*100.0;
		
		while(propValue<0){
			Thread.sleep(100);
			propValue = osBean.getSystemCpuLoad()*100.0;
		}
		
		System.out.println("Property publish for property "+propKey+" value = "+propValue);
		mqttClient.getWorker().Property().publish(thingKey, null, propKey, propValue, null, resp);

		if(!resp.isSuccess()){
			System.out.println("Failed to publish Data for property cpu_usage for thing with key "+thingKey+"with error "+resp.getErrormessages()[0]+" ("+resp.getErrorcodes()+")");
		}

	}
*/

	public void tr50_connect_mqtt() throws MqttException, IOException {

		mqttClient = new DwMqttClient();
		mqttClient.initialize(openUrl, true, "tr50_sysInfo_client");
		mqttClient.authenticate(thingKey, appToken);

	}

	public static void printHelpInfo() {

		System.out.println("Usage: -o openserverurl -a appToken -t thingKey [-r poll rate]");

		System.out.println("\t-o\tOpen Server URL");
		System.out.println("\t-t\tThing Key to Publish Sys Info Data ");
		System.out.println("\t-a\tApplication Token to Authenticate to an Open Server Organization");
		System.out.println("\t-r\tPoll Rate for Sys Info Data. Default is 30 seconds");
		System.out.println("");

	}

	public static void parseArgs(SysInfoSample sysinfo, String[] args) throws Exception {

		for(int i=0;i<args.length;i++){

			if ("-o".equals(args[i])) 
			{
				sysinfo.setOpenUrl(args[++i]);
			}
			else if ("-t".equals(args[i])) 
			{
				sysinfo.setThingKey(args[++i]);
			}
			else if ("-a".equals(args[i])) 
			{
				sysinfo.setAppToken(args[++i]);
			}
			else if ("-r".equals(args[i])) 
			{
				sysinfo.setPollRate(Integer.parseInt(args[++i]));
			}
			else throw new Exception("Unknown argument: "+args[i]);
		}

	}

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public String getThingKey() {
		return thingKey;
	}

	public void setThingKey(String thingKey) {
		this.thingKey = thingKey;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public DwMqttClient getMqttClient() {
		return mqttClient;
	}

	public void setMqttClient(DwMqttClient mqttClient) {
		this.mqttClient = mqttClient;
	}

	public int getPollRate() {
		return pollRate;
	}

	public void setPollRate(int pollRate) {
		this.pollRate = pollRate;
	}

}
