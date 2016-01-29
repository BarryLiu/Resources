package com.example.json;

public class NewsBean {
	private String detail;
	private String time;
	private int commit;
	private String imagePath;
	public NewsBean(String detail, String time, int commit, String imagePath) {
		super();
		this.detail = detail;
		this.time = time;
		this.commit = commit;
		this.imagePath = imagePath;
	}
	public NewsBean() {
		super();
	}
	@Override
	public String toString() {
		return "NewsBean [detail=" + detail + ", time=" + time + ", commit="
				+ commit + ", imagePath=" + imagePath + "]";
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getCommit() {
		return commit;
	}
	public void setCommit(int commit) {
		this.commit = commit;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
