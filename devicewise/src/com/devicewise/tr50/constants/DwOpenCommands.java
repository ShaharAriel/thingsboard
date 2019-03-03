package com.devicewise.tr50.constants;

public class DwOpenCommands {
	
	public static int CMD_TIMEOUT_MILLIS = 60000;
	
	public static final String 		CORR_ID_ACK = "ack",
									CORR_ID_AUTH = "auth",
									CORR_ID_CMD = "cmd",
									CORR_ID_DATA = "data",
									CORR_ID_DO = "do",
									CORR_ID_EXEC = "exec",
			 						CORR_ID_LIST = "list",
			 						CORR_ID_PUT = "put",
			 						CORR_ID_GET = "get",
									CORR_ID_GENERIC = "generic";
	

	public static final String 	CMD_API_AUTH = "api.authenticate",
			
								CMD_API_ALARM_HISTORY = "alarm.history",
								CMD_API_ALARM_PUBLISH = "alarm.publish",
								
								CMD_API_APP_CREATE = "app.create",
								CMD_API_APP_DELETE = "app.delete",
								CMD_API_APP_FIND = "app.find",
								CMD_API_APP_LIST = "app.list",
								CMD_API_APP_UPDATE = "app.update",
								
								CMD_API_CONFIG_DESC = "config.desc",
								CMD_API_CONFIG_GET = "config.get",
								CMD_API_CONFIG_SET = "config.set",

								CMD_API_DIAG_CHECK = "diag.check",
								CMD_API_DIAG_CLUSTER = "diag.cluster",
								CMD_API_DIAG_CONN_MQTT_LIST = "diag.conn.mqtt.list",
								CMD_API_DIAG_ECHO = "diag.echo",
								CMD_API_DIAG_MONGO_STATS = "diag.mongo.stats",
								CMD_API_DIAG_PING = "diag.ping",
								CMD_API_DIAG_THING_STATS = "diag.thing.stats",
								CMD_API_DIAG_TIME = "diag.time",

								CMD_API_EMAIL_SEND = "email.send",

								CMD_API_FILE_DELETE = "file.delete",
								CMD_API_FILE_GET = "file.get",
								CMD_API_FILE_LIST = "file.list",
								CMD_API_FILE_PUT = "file.put",
								CMD_API_FILE_TAG_CLOUD = "file.tag.cloud",

								CMD_API_GATEWAY_REGISTER = "gateway.register",

								CMD_API_GEOFENCE_CREATE = "geofence.create",
								CMD_API_GEOFENCE_DELETE = "geofence.delete",
								CMD_API_GEOFENCE_FIND = "geofence.find",
								CMD_API_GEOFENCE_HISTORY = "geofence.history",
								CMD_API_GEOFENCE_LIST = "geofence.list",
								CMD_API_GEOFENCE_LOCATE = "geofence.locate",
								CMD_API_GEOFENCE_TAG_CLOUD = "geofence.tag.cloud",
								CMD_API_GEOFENCE_UPDATE = "geofence.update",

						 		CMD_API_LOCALE_GET = "locale.get",
							    CMD_API_LOCALE_LIST = "locale.list",
							    CMD_API_LOCALE_SET = "locale.set",

							    CMD_API_LOG_LIST = "log.list",
							    CMD_API_LOG_PUBLISH = "log.publish",

						 		CMD_API_LOCATION_DECODE = "location.decode",
						 		CMD_API_LOCATION_ENCODE = "location.encode",
						 		CMD_API_LOCATION_HISTORY = "location.history",
    							CMD_API_LOCATION_PUBLISH = "location.publish",
								CMD_API_LOCATION_STATS = "location.stats",
								CMD_API_LOCATION_WEATHER = "location.weather",
								
						 		CMD_API_MAILBOX_ACK = "mailbox.ack",
						 		CMD_API_MAILBOX_CHECK = "mailbox.check",
				 				CMD_API_MAILBOX_FIND = "mailbox.find",
						 		CMD_API_MAILBOX_LIST = "mailbox.list",
						 	    CMD_API_MAILBOX_PURGE = "mailbox.purge",
    							CMD_API_MAILBOX_SEND = "mailbox.send",
    							CMD_API_MAILBOX_SUMMARY = "mailbox.summary",
								CMD_API_MAILBOX_UPDATE = "mailbox.update",
								
								CMD_API_METHOD_EXEC = "method.exec",
								
								CMD_API_MODULE_REGISTER = "module.register",

								CMD_API_ORG_CREATE = "org.create",
								CMD_API_ORG_DELETE = "org.delete",
								CMD_API_ORG_FIND = "org.find",
								CMD_API_ORG_LIST = "org.list",
								CMD_API_ORG_UPDATE = "org.update",
								
								CMD_API_PROPERTY_AGGREGATE = "property.aggregate",
								CMD_API_PROPERTY_BATCH = "property.batch",
								CMD_API_PROPERTY_CURRENT = "property.current",
								CMD_API_PROPERTY_HISTORY = "property.history",
								CMD_API_PROPERTY_PUBLISH = "property.publish",
								CMD_API_PROPERTY_STATS = "property.stats",
								
								CMD_API_ROLE_CREATE = "role.create",
								CMD_API_ROLE_DELETE = "role.delete",
								CMD_API_ROLE_FIND = "role.find",
								CMD_API_ROLE_LIST = "role.list",
								CMD_API_ROLE_PERMS = "role.perms",
								CMD_API_ROLE_UPDATE = "role.update",

								CMD_API_SESSION_FIND = "session.find",
								CMD_API_SESSION_INFO = "session.info",
								CMD_API_SESSION_LIST = "session.list",
								CMD_API_SESSION_ORG_LIST = "session.org.list",
								CMD_API_SESSION_ORG_SWITCH = "session.org.switch",

								CMD_API_THING_ATTR_ADD = "thing.attr.add",
							    CMD_API_THING_ATTR_DEL = "thing.attr.delete",
							    CMD_API_THING_ATTR_GET = "thing.attr.get",
								CMD_API_THING_ATTR_SET = "thing.attr.set",
							    CMD_API_THING_ATTR_UNSET = "thing.attr.unset",
								CMD_API_THING_BIND   = "thing.bind",
							    CMD_API_THING_CREATE   = "thing.create",
							    CMD_API_THING_DELETE   = "thing.delete",
							    CMD_API_THING_FIND = "thing.find",
							    CMD_API_THING_LIST = "thing.list",
							    CMD_API_THING_SECTAG_ADD = "thing.sectag.add",
							    CMD_API_THING_SECTAG_CLOUD = "thing.sectag.cloud",
							    CMD_API_THING_SECTAG_DELETE = "thing.sectag.delete",
							    CMD_API_THING_TAG_ADD = "thing.tag.add",
							    CMD_API_THING_TAG_CLOUD = "thing.tag.cloud",
							    CMD_API_THING_TAG_DELETE = "thing.tag.delete",
							    CMD_API_THING_UNBIND   = "thing.unbind",
							    CMD_API_THING_UPDATE = "thing.update",
							    
							    CMD_API_THINGDEF_CREATE   = "thing_def.create",
							    CMD_API_THINGDEF_DELETE   = "thing_def.delete",
							    CMD_API_THINGDEF_FIND = "thing_def.find",
							    CMD_API_THINGDEF_LIST = "thing_def.list",
    							CMD_API_THINGDEF_UPDATE = "thing_def.update",

							    CMD_API_TRIGGER_CREATE   = "trigger.create",
							    CMD_API_TRIGGER_DELETE   = "trigger.delete",
							    CMD_API_TRIGGER_FIND = "trigger.find",
							    CMD_API_TRIGGER_LIST = "trigger.list",
							    CMD_API_TRIGGER_LIST_ACTIONS = "trigger.list.actions",
							    CMD_API_TRIGGER_LIST_EVENTS = "trigger.list.events",
								CMD_API_TRIGGER_UPDATE = "trigger.update",
								
							    CMD_API_TUNNEL_CONN_HISTORY   = "tunnel.conn.history",
							    CMD_API_TUNNEL_HISTORY   = "tunnel.history",
							    CMD_API_TUNNEL_OPEN = "tunnel.open",
							    CMD_API_TUNNEL_ROUTER_LIST = "tunnel.router.list",
							    CMD_API_TUNNEL_CLOSE = "tunnel.close",
								
							    CMD_API_USAGE_API_HISTORY   = "usage.api.history",
							    CMD_API_USAGE_API_LIST   = "usage.api.list",
							    CMD_API_USAGE_STORAGE_HISTORY = "usage.storage.history",
							    CMD_API_USAGE_THING_HISTORY = "usage.thing.history",
								
							    CMD_API_USER_CREATE   = "user.create",
							    CMD_API_USER_DELETE   = "user.delete",
							    CMD_API_USER_FIND = "user.find",
							    CMD_API_USER_LIST = "user.list",
							    CMD_API_USER_ORG_ADD = "user.org.add",
							    CMD_API_USER_ORG_LIST = "user.org.list",
							    CMD_API_USER_ORG_REMOVE = "user.org.remove",
								CMD_API_USER_ORG_TAG_CLOUD = "user.org.remove",
							    CMD_API_USER_ORG_UPDATE = "user.org.update",
								CMD_API_USER_UPDATE = "user.update",
	
								CMD_API_TWILIO_SMS_SEND = "twilio.sms.send";
}
