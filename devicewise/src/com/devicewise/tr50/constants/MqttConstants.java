package com.devicewise.tr50.constants;


public class MqttConstants {
	
	public static final int QOS_AT_MOST_ONCE = 0,
			QOS_AT_LEAST_ONCE = 1,
		    QOS_EXACTLY_ONCE = 2;

	public static final String DEFAULT_MQTTHOST = "open02.devicewise.com";
//	public static final String DEFAULT_MQTTHOST = "172.27.9.92";
//	public static final String DEFAULT_MQTTHOST = "127.0.0.1";
	public static final String DEFAULT_MQTTPORT = "8883";
	public static final String DEFAULT_PROTOCOL = "ssl://";
	public static final short DEFAULT_KEEPALIVE = 60;
	public static final int	  MAX_CLIENT_ID_LENGTH = 23;
	public static final String DEFAULT_MQTT_USER = "dwopenjclient" ;
	public static final String DEFAULT_MQTTPASSWORD = DwOpenServer.DEFAULT_APPLICATION_TOKEN;
	
	public static final String MQTT_SSL_PORT="8883";
	public static final String MQTT_TCP_PORT="1883";
	
}
