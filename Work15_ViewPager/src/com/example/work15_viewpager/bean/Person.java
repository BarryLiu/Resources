package com.example.work15_viewpager.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 继承Parcelable  用于数据源的传递
 * 
 * @author Barry
 *
 */
public class Person implements Parcelable {
	private String name;
	private String tel;
	private int id;

	 

	@Override
	public String toString() {
		return "Person [name=" + name + ", tel=" + tel + ", id=" + id + "]";
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public int describeContents() {
		return 0;
	}

//===================实现Parcelable 接口必须下面这样写    AndroidStudio  可以直接实现代码自己写好
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(tel);
		dest.writeInt(id);
	}

	protected Person(Parcel in) {
		name = in.readString();
		tel = in.readString();
		id = in.readInt();
	}

	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
		public Person createFromParcel(Parcel in) {
			return new Person(in);
		}

		public Person[] newArray(int size) {
			return new Person[size];
		}
	};

}
