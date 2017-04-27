package com.infamous.fdsa.myfoody.webservice.restful.bean;

public class LoginCredential {
	private String userID;
	private String password;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginCredential(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}
	
}
