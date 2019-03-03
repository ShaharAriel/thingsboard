package com.devicewise.tr50.interfaces;

import java.util.LinkedHashMap;

public interface IDwCommand {
		
	public abstract void setParams(LinkedHashMap<String, Object> params);
	public abstract LinkedHashMap<String,Object> getParams();
	public abstract void addParam(String param, Object value);
	public abstract void deleteParam(String param);
	public abstract void setCommand(String command);
	public abstract String getCommand();
}
