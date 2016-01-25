package com.example.work5;

public class Movie {
	private String name;
	private String upto;
	private int resid;
	public Movie(String name, String upto, int resid) {
		this.name=name;
		this.upto=upto;
		this.resid=resid;
	}
	@Override
	public String toString() {
		return "Movie [name=" + name + ", upto=" + upto + ", resid=" + resid
				+ "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpto() {
		return upto;
	}
	public void setUpto(String upto) {
		this.upto = upto;
	}
	public int getResid() {
		return resid;
	}
	public void setResid(int resid) {
		this.resid = resid;
	}
	
	

}
