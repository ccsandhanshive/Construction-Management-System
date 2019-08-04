package com.services;

public interface Contractor {
	int retriveData(String sitename);
	int retriveData(String sitename,String date);
	int deleteData(String sitename);
	int deleteData(String sitename,String date);
	int laborRateChange(int rate);
	int workerRateChange(int rate);
	int cementRateChange(int rate);
	int sandRateChange(int rate);
	int brickRateChange(int rate);
	String insertUser(String sitename,String role);
	int supervisorSiteChange(int username,String newsite);
	int customerSiteChange(int username,String password,String sitename);

}
