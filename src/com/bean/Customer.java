package com.bean;

public class Customer {
private int userid;
private String password;
public Customer(int userid, String password) {
	super();
	this.userid = userid;
	this.password = password;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Customer [userid=" + userid + ", password=" + password + "]";
}


}
