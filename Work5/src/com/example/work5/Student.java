package com.example.work5;

public class Student {
	private String name;
	private String tel;
	private int resId;
	
	private Boolean isChecked;// «∑Ò—°÷–
	 
	public Student(String name, String tel, int resId, Boolean isChecked) {
		super();
		this.name = name;
		this.tel = tel;
		this.resId = resId;
		this.isChecked = isChecked;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", tel=" + tel + ", resId=" + resId
				+ "]";
	}
	public Boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	
	
}
