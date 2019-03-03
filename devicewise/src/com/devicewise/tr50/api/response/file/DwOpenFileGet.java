package com.devicewise.tr50.api.response.file;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenFileGet extends DwOpenGenericResponse{
	
	protected String fileId;
	protected int fileSize;
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}


}
