package io.barryliu.work13_contact.bean;

public class Person {
	private String name;
	private String tel
	;
	@Override
	public String toString() {
		return "Person [name=" + name + ", tel=" + tel + "]";
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
	public Person(String name, String tel) {
		super();
		this.name = name;
		this.tel = tel;
	}
	
}
