package com.example.ed;

public class NewsBean {
	private String name;
	private String iconPath;

	public NewsBean(String name, String iconPath) {
		super();
		this.name = name;
		this.iconPath = iconPath;
	}

	public NewsBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	@Override
	public String toString() {
		return "NewsBean [name=" + name + ", iconPath=" + iconPath + "]";
	}
}
