package com.example.work15_viewpager;

import java.util.ArrayList;
import java.util.List;

public class ContactBean {
	String title;
	List<Person> childList = new ArrayList<Person>();
	@Override
	public String toString() {
		return "ContactBean [title=" + title + ", childList=" + childList + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Person> getChildList() {
		return childList;
	}
	public void setChildList(List<Person> childList) {
		this.childList = childList;
	}
	
	
}
