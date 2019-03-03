package com.devicewise.tr50.api.response.file;

import java.util.ArrayList;

import com.devicewise.tr50.api.response.DwOpenGenericResponse;

public class DwOpenFileList extends DwOpenGenericResponse{
	
	private ArrayList<File> result;
	
	public static class File{
		
		private String id;
		private String fileName;
		private boolean Public;
		private String uploadDate;
		private int length;
		private String[]tags;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUploadDate() {
			return uploadDate;
		}
		public void setUploadDate(String uploadDate) {
			this.uploadDate = uploadDate;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public String[] getTags() {
			return tags;
		}
		public void setTags(String[] tags) {
			this.tags = tags;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public boolean isPublic() {
			return Public;
		}
		public void setPublic(boolean _public) {
			Public = _public;
		}
	}

	public ArrayList<File> getResult() {
		return result;
	}

	public void setResult(ArrayList<File> result) {
		this.result = result;
	}

}
