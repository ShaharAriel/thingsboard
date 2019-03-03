package com.devicewise.tr50.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import com.devicewise.tr50.constants.DwOpenErrors;
import com.devicewise.tr50.exception.DwOpenException;
import com.devicewise.tr50.helpers.StringReply;
import com.devicewise.tr50.protocol.DwClient;

public class DwHttpClient extends DwClient {
	
	private HttpURLConnection connection;
	private URL url = null;
	private int connect_timeout = 0;
	private int read_timeout = 0;
	private Proxy proxy = Proxy.NO_PROXY;
	
	public DwHttpClient() throws IOException{
		super();
	}
	
	private void _trustAllCertificates() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] { //Create a trust manager that does not validate certificate chains
			    new X509TrustManager() {     
			        public X509Certificate[] getAcceptedIssuers() {
			            return new X509Certificate[0];
			        } 
			        public void checkClientTrusted( 
			            X509Certificate[] certs, String authType) {
			            } 
			        public void checkServerTrusted( 
			            X509Certificate[] certs, String authType) {
			        }
			    } 
		}; 
				
        SSLContext sc = SSLContext.getInstance("SSL"); //Install the all-trusting trust manager
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        HostnameVerifier allHostsValid = new HostnameVerifier() { //Create all-trusting host name verifier
			@Override
			public boolean verify(String arg0, SSLSession session) {
				return true;
			}
        };    
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid); //Install the all-trusting host verifier
	}

	public int connect(int connect_timeout, int read_timeout, boolean trust_all_certs) throws DwOpenException {
		
		if(url == null) {
			return(DwOpenErrors.PROTOCOL_HTTP_URL_NOT_INITIALIZED);
		}

		try {
			if(proxy == Proxy.NO_PROXY) {
				setConnection((HttpURLConnection)getURL().openConnection()); 
			} else {
				setConnection((HttpURLConnection)getURL().openConnection(proxy)); 
			}
		} catch (IOException e) {
			throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_OPEN_URL_CONNECTION_FAILED, "DwHttpClient[connect()]: (" + e.getClass().getSimpleName() + ") " + e.getMessage());
		}
		
		if(connection == null) {
			throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_CONNECTION_NOT_INITIALIZED);
		}
		
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_SET_METHOD_POST_FAILED, "DwHttpClient[connect()]: Requesting method POST failed: (" 
																+ e.getClass().getSimpleName() + ") " + e.getMessage());
		}
		
		connection.setDoOutput(true);
		connection.setAllowUserInteraction(true);
		connection.setDoInput(true);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type","application/json; charset=utf-8"); 
	    connection.setRequestProperty("Content-Language", "en-US");
		connection.setConnectTimeout(connect_timeout);
		connection.setReadTimeout(read_timeout);

		this.connect_timeout = connect_timeout;
		this.read_timeout = read_timeout;
		
		try {
			connection.connect();
		} catch (SSLException SSLException) {
			if(!trust_all_certs) {
				throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_CERTIFICATE_FAILED, "This Connection's certificate is untrusted.\n");
			}
			
			try {
				_trustAllCertificates();
		        connect();
			} catch (Exception e) {
				throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_TRUST_ALL_CERTS_FAILED, "Failed to trust all certs: (" + e.getClass().getSimpleName() + ") " + e.getMessage());
			}
		} catch (IOException e) {
			throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_CONNECT_FAILED, "DwHttpClient[connect()]: Connection failed: (" 
																+ e.getClass().getSimpleName() + ") " + e.getMessage());
		}
		
		return 0;
	}

	@Override
	public int connect() throws DwOpenException {
		return connect(this.connect_timeout, this.read_timeout, false);
	}
	
	public int connect(int connect_timeout, int read_timeout) throws DwOpenException {
		return connect(connect_timeout, read_timeout, false);
	}

	@Override
	public int disconnect() {
		if(connection == null) {
			return(DwOpenErrors.PROTOCOL_HTTP_CONNECTION_NOT_INITIALIZED);
		}
		
		connection.disconnect();
		return 0;
	}

	@Override
	public int sendJSONRequest(int msgId, String json, StringReply reply) throws DwOpenException {
		if (connect() != 0) {
			return(DwOpenErrors.PROTOCOL_HTTP_CONNECTION_NOT_INITIALIZED);
		}
		
		try {
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	        writer.write(json);
	        writer.close();
		} catch (IOException e) {
			throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_WRITE_FAILED, "DwHttpClient[sendJSONRequest()]: (" 
																+ e.getClass().getSimpleName() + ") " + e.getMessage());
		}
		
		try {
			StringBuilder responseBuilder = new StringBuilder();
			InputStreamReader inputReader = new InputStreamReader(connection.getInputStream());
	        BufferedReader bufferedReader = new BufferedReader(inputReader);
	        String line = bufferedReader.readLine();
	        while(line != null && !line.isEmpty()){
	        	responseBuilder.append(line);
	        	responseBuilder.append('\n');
	            line = bufferedReader.readLine();
	        }
	        reply.setValue(responseBuilder.toString());
		} catch (IOException e) {
			throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_READ_FAILED, "DwHttpClient[sendJSONRequest()]: (" 
																+ e.getClass().getSimpleName() + ") " + e.getMessage());
		}
		
		
		try {
			int httprc = connection.getResponseCode();

	        if (httprc != HttpURLConnection.HTTP_OK) {
	        	return(DwOpenErrors.PROTOCOL_HTTP_REQUEST_UNSUCESSFUL);
	        }
		} catch (IOException e) {
			throw new DwOpenException(DwOpenErrors.PROTOCOL_HTTP_GET_RESPONSE_CODE_FAILED, "DwHttpClient[sendJSONRequest()]: (" 
															+ e.getClass().getSimpleName() + ") " + e.getMessage());
		}
        
        disconnect();
		return 0;
	}

	@Override
	public int sendAsyncJSONRequest(int msgId, String json) throws DwOpenException {
		//just return an error here for now per Jim
		return DwOpenErrors.PROTOCOL_HTTP_ASYNC_JSON_NOT_IMPLEMENTED;
	}
	
	public int initialize(String url) {
		try {
			setURL(new URL(url));
			return 0;
		} catch (IOException e) {
			System.err.println("DwHttpClient[initialize()]: (" + e.getClass().getSimpleName() + ") " + e.getMessage());
        	return(DwOpenErrors.PROTOCOL_HTTP_SET_URL_FAILED);
		}
	}
	
	public void setProxy(Proxy proxy) {
		if(proxy == null) {
			this.proxy = Proxy.NO_PROXY;
			return;
		}
		this.proxy = proxy;
	}
	
	public Proxy getProxy() {
		return proxy;
	}
	
	private void setURL(URL url) {
		this.url = url;
	}
	
	private URL getURL() {
		return url;
	}

	public HttpURLConnection getConnection() {
		return connection;
	}

	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}
}


