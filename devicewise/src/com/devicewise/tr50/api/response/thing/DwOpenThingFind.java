package com.devicewise.tr50.api.response.thing;

import java.util.LinkedHashMap;

import com.devicewise.tr50.api.params.DwOpenAlarmParam;
import com.devicewise.tr50.api.params.DwOpenLocationParam;
import com.devicewise.tr50.api.params.DwOpenThingAttribute;
import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenThingFind extends DwOpenGenericResponse{

	private String id;
	private String name;
	private String key;
	private String appId;
	private String desc;
	private String sessionId;
	private String defId;
	private String defKey;
	private String[] tags;
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	private String iccid;
	private String esn;
	private String[] secTags;
	private String lastSeen;
	private String locUpdated;
	private boolean locEnabled;
	private boolean connected;
	private DwOpenLocationParam loc;
	private String tunnelActualHost;
	private String tunnelVirtualHost;
	private LinkedHashMap<String,Integer> tunnelLatencies;
	private LinkedHashMap<String,DwOpenThingAttribute> attrs;
	private LinkedHashMap<String,DwOpenAlarmParam> states;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getDefId() {
		return defId;
	}
	public void setDefId(String defId) {
		this.defId = defId;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getEsn() {
		return esn;
	}
	public void setEsn(String esn) {
		this.esn = esn;
	}
	public String[] getSecTags() {
		return secTags;
	}
	public void setSecTags(String[] secTags) {
		this.secTags = secTags;
	}
	public String getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}
	public String getLocUpdated() {
		return locUpdated;
	}
	public void setLocUpdated(String locUpdated) {
		this.locUpdated = locUpdated;
	}
	public LinkedHashMap<String,DwOpenThingAttribute> getAttrs() {
		return attrs;
	}
	public void setAttrs(LinkedHashMap<String,DwOpenThingAttribute> attributes) {
		this.attrs = attributes;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
/*
	@Override
	public void parseResponse(String json) throws JsonParseException, JsonMappingException, IOException, DwOpenException{
		
		setJsonResponse(json);
		parseGenericResponse(json);
		if(this.isSuccess()){
			parseCustomResponse(json);
			parseAttributes(json);
		}
	}
	
	private void parseAttributes(String json) throws JsonParseException, IOException {
		
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(json);

		jp.nextToken();

		attributes= new ArrayList<DwOpenThingAttribute>();
		
		while(!jp.isClosed() && !jp.getText().equalsIgnoreCase("attrs")){
			jp.nextToken();
		}

		if(jp.isClosed())
			return;
		
		while(!jp.isClosed()){
			if(jp.nextToken()==JsonToken.END_OBJECT)
				break;
			if(jp.getCurrentToken()==JsonToken.FIELD_NAME){
				DwOpenThingAttribute attr = new DwOpenThingAttribute();
				attr.setKey(jp.getCurrentName());
				jp.nextToken();
				while(jp.getCurrentToken()!=JsonToken.END_OBJECT){
					
					if(jp.getText().equalsIgnoreCase("name")){
						jp.nextToken();
						attr.setName(jp.getText());
					}
					
					else if(jp.getText().equalsIgnoreCase("value")){
					jp.nextToken();
					attr.setValue(jp.getText());
					}
					jp.nextToken();
				}
				attributes.add(attr);
			}
		}
		
		
	}
*/	
	public LinkedHashMap<String,DwOpenAlarmParam> getStates() {
		return states;
	}
	public void setStates(LinkedHashMap<String,DwOpenAlarmParam> states) {
		this.states = states;
	}
	public boolean isLocEnabled() {
		return locEnabled;
	}
	public void setLocEnabled(boolean locEnabled) {
		this.locEnabled = locEnabled;
	}
	public DwOpenLocationParam getLoc() {
		return loc;
	}
	public void setLoc(DwOpenLocationParam loc) {
		this.loc = loc;
	}
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	public String getTunnelActualHost() {
		return tunnelActualHost;
	}
	public void setTunnelActualHost(String tunnelActualHost) {
		this.tunnelActualHost = tunnelActualHost;
	}
	public String getTunnelVirtualHost() {
		return tunnelVirtualHost;
	}
	public void setTunnelVirtualHost(String tunnelVirtualHost) {
		this.tunnelVirtualHost = tunnelVirtualHost;
	}
	public LinkedHashMap<String,Integer> getTunnelLatencies() {
		return tunnelLatencies;
	}
	public void setTunnelLatencies(LinkedHashMap<String,Integer> tunnelLatencies) {
		this.tunnelLatencies = tunnelLatencies;
	}
	public String getDefKey() {
		return defKey;
	}
	public void setDefKey(String defKey) {
		this.defKey = defKey;
	}
}
