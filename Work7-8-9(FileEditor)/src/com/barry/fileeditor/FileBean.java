package com.barry.fileeditor;

public class FileBean {
	private String filePath;
	private String fileName;
	@Override
	public String toString() {
		return "FileBean [filePath=" + filePath + ", fileName=" + fileName
				+ "]";
	}
	public FileBean(String filePath, String fileName) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
	}
	public FileBean() {
		super();
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
