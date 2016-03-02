package com.example.volley;

public class DataBean {
	private String str;
	private String image;
	public DataBean(){}
	public DataBean(String str, String image) {
		super();
		this.str = str;
		this.image = image;
	}
	@Override
	public String toString() {
		return "DataBean [str=" + str + ", image=" + image + "]";
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	 

	 
}
