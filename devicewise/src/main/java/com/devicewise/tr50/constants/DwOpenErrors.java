package com.devicewise.tr50.constants;

public class DwOpenErrors {

	public static final int API_REPLY_NULL = -1000,
					        API_REPLY_BLANK = -1001,
					        API_REPLY_BAD = -1002,
							API_REPLY_ERROR = -1003,					        
							API_REPLY_NOT_FOUND = -1004,
							API_REPLY_NO_CORR_ID = -1005,
						    API_REPLY_PARSE_ERROR = -1006,
						    API_REPLY_ASYNC_NOT_FOUND = -1007,
						    API_REPLY_PARAM_MISMATCH = -1008,
													 
					        API_UNITIALIZED_REPLY_OBJECT = -1100,
							API_UNITIALIZED_PARAM_OBJECT = -1101,
							API_UNITIALIZED_ASYNC_RECEIVER_OBJECT = -1102,
							API_UNINITIALIZED_METHOD = -1103,

					        API_UNKNOWN_REPLY_OBJECT = -1200,
					        API_UNKNOWN_SENDLISTENER_OBJECT = -1201,
					        API_UNKNOWN_RECEIVELISTENER_OBJECT = -1202,
					        API_UNKNOWN_DISCONNECTLISTENER_OBJECT = -1203,
					        API_UNKNOWN_MAILBOX_ITEM_OBJECT = -1204,
					        API_UNKNOWN_MAILBOX_COMMAND_OBJECT = -1205,
					        					        
					        API_INSUFFICIENT_PARAMS = -1300,
					        
					        API_BAD_PARAM_OBJECT = -1400,
					        
					        API_METHOD_ALREADY_REGISTERED = -1500,
					        API_METHOD_NOT_REGISTERED = -1501,
					        API_METHOD_UNINITIALIZED = -1502,
					        API_METHOD_UNKNOWN = -1503,
					        API_METHOD_NULL = -1504,
					        
					        API_MAILBOX_COMMAND_ALREADY_REGISTERED = -1600,
					        API_MAILBOX_ITEM_ALREADY_REGISTERED = -1601,
					        
					        API_COMMAND_KEY_NOT_INITIALIZED = -1700,
					        API_COMMAND_CORR_ID_NOT_SET = -1701,
					        API_COMMAND_CORR_ID_EXISTS = -1702,
					        API_COMMAND_ADD_FAILED_PACKET_CLOSED = -1702,
					        
							PROTOCOL_CLIENT_AUTHENTICATION_FAILED = -9002,
							PROTOCOL_CLIENT_NOT_CONNECTED = -9001,
							PROTOCOL_SEND_TIMEOUT = -9002,
							PROTOCOL_CLIENT_NOT_INITIALIZED = -9003,

							PROTOCOL_HTTP_URL_NOT_INITIALIZED = -10000,
							PROTOCOL_HTTP_CONNECTION_NOT_INITIALIZED = -10001,
							PROTOCOL_HTTP_REQUEST_UNSUCESSFUL = -10002,
							PROTOCOL_HTTP_ASYNC_JSON_NOT_IMPLEMENTED = -10003, 
							PROTOCOL_HTTP_OPEN_URL_CONNECTION_FAILED = -10004,
							PROTOCOL_HTTP_SET_METHOD_POST_FAILED = -10005,
							PROTOCOL_HTTP_CONNECT_FAILED = -10006,
							PROTOCOL_HTTP_WRITE_FAILED = -10007,
							PROTOCOL_HTTP_READ_FAILED = -10008,
							PROTOCOL_HTTP_GET_RESPONSE_CODE_FAILED = -10009,
							PROTOCOL_HTTP_SET_URL_FAILED = -10010,
							PROTOCOL_HTTP_CERTIFICATE_FAILED = -10011,
							PROTOCOL_HTTP_TRUST_ALL_CERTS_FAILED = -10012;
	

}
