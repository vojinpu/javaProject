package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import enums.UserType;

public class User {
	
	@JsonProperty("name")
	private String mName;
	@JsonProperty("password")
	private String mPassword;
	@JsonProperty("userType")
	private UserType mUserType;
	
	public User() {
		
	}
	
	public User (String name, String password, UserType type) {
		mName = name;
		mPassword = password;
		mUserType = type;
	}
	
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public String getPassword() {
		return mPassword;
	}
	public void setPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	public UserType getUserType() {
		return mUserType;
	}
	public void setUserType(UserType mUserType) {
		this.mUserType = mUserType;
	}
}
