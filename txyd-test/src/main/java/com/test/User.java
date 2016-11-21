package com.test;

public class User {
	Integer id;
	String userName;
	String password;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public int hashCode(){
		return 0;
	}
	public boolean equals(Object obj){
		return true;
	}
	public int hash(){
		return 0;
	}
}