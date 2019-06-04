package com.it.domain;


public class User {
	private String uid;
	private String username;
	private String password;
	private String phone;
	
	
	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password +
				", phone=" + phone + "]";
	}
	
}
