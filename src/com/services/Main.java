package com.services;

public interface Main {
	String login(int userid,String password);        //login user
	int changePass(int username,String password,String newpassword); 		//change in password
	
}
