package com.example.work9;

import android.os.Parcel;
import android.os.Parcelable;

public class Teacher implements Parcelable{
	private String name;
	private String sex;
	private Integer age;
	public Teacher() {
		super();
	}
	public Teacher(String name, String sex, Integer age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
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
		return "Teacher [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
 
	
	 protected Teacher(Parcel in) {
	        //需要和写入的顺序一致
	        name = in.readString();
	        sex = in.readString();
	        age = in.readInt();

	    }

	    /**
	     * 创建了一个内部类用于读取数据
	     */
	    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
	        @Override
	        public Teacher createFromParcel(Parcel in) {
	            return new Teacher(in);
	        }

	        @Override
	        public Teacher[] newArray(int size) {
	            return new Teacher[size];
	        }
	    };

	    @Override
	    public int describeContents() {
	        return 0;
	    }

	    /**
	     * 将数据写入
	     * @param dest
	     * @param flags
	     */
	    @Override
	    public void writeToParcel(Parcel dest, int flags) {
	        //将数据写入到dest中
	        dest.writeString(name);//写入名字
	        dest.writeString(sex);//写入sex
	        dest.writeInt(age);
	    }
	
}
