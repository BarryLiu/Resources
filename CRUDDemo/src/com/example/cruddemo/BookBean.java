package com.example.cruddemo;

import java.io.Serializable;

public class BookBean implements Serializable{
	private Integer id;
	private String name;
	private String author;
	private String data;
	 
	@Override
	public String toString() {
		return "BookBean [id=" + id + ", name=" + name + ", author=" + author
				+ ", data=" + data + "]";
	}
	public BookBean(String name, String author, String data) {
		super();
		this.name = name;
		this.author = author;
		this.data = data;
	}
	public BookBean(Integer id, String name, String author, String data) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.data = data;
	}
	public BookBean() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
