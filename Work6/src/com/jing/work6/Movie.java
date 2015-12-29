package com.jing.work6;

/**
 * 电影实体类
 * 用于自定义适配器中携带数据
 * @author Barry
 *
 */
public class Movie {
	//头像图片的id
	private int resId;
	//电影名称
	private String name;
	public Movie(){
	}
	public Movie(int resId, String name) {
		super();
		this.resId = resId;
		this.name = name;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Movie [resId=" + resId + ", name=" + name + "]";
	}
	
	
}
