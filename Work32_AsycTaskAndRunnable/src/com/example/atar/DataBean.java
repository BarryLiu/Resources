package com.example.atar;

public class DataBean {
	private String name;
	private String imageName;
	public DataBean(){}
	public DataBean(String name, String imageName) {
		super();
		this.name = name;
		this.imageName = imageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "DataBean [name=" + name + ", imageName=" + imageName + "]";
	}

}
