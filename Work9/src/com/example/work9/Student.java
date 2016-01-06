package com.example.work9;

import java.io.Serializable;

public class Student implements Serializable {
	private String name;
	private String sex;
	private Integer age;
	public Student(String name, String sex, Integer age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public Student() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
	
}
