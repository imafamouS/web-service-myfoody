package com.infamous.fdsa.myfoody.webservice.restful.bean;

public class UserBean {
	
	private String userid;
	private String name;
	private String phone;
	private String address;
	private String age;
	private String avatar;
	
	public UserBean(String userid,String name, String phone, String address, String age, String avatar) {
		this.userid = userid;
		this.phone = phone;
		this.address = address;
		this.age = age;
		this.avatar = avatar;
		this.name=name;
	}
	
	public UserBean(String userid,String name, String phone, String address, String age) {
		this.userid = userid;
		this.phone = phone;
		this.address = address;
		this.age = age;
		this.name=name;
	}
	public UserBean(){
		
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
