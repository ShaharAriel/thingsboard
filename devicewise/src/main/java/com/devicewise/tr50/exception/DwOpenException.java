package com.devicewise.tr50.exception;

public class DwOpenException extends Exception {
	
	private static final long serialVersionUID = 6396456853943332871L;
	
	int errorCode = 0;
	
	public DwOpenException(Exception e){
		super(e);
	}
	
	public DwOpenException(String _exception) {
		super(_exception);
	}
	
	public DwOpenException(int _errorCode) {
		super();
		errorCode = _errorCode;
	}
	
	public DwOpenException(int _errorCode, String _exception) {
		super(_exception);
		errorCode = _errorCode;
	}
	
	public int getErrorCode() { 
		return errorCode; 
	}
	
	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}
}

